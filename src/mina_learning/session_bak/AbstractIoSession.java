package mina_learning.session_bak;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.FileChannel;

public class AbstractIoSession {

	public final ReadFuture read() {
		if (!getConfig().isUseReadOperation()) {
			throw new IllegalStateException("useReadOperation is not enabled.");
		}

		Queue<ReadFuture> readyReadFutures = getReadyReadFutures();
		ReadFuture future;
		synchronized (readyReadFutures) {
			future = readyReadFutures.poll();
			if (future != null) {
				if (future.isClosed()) {
					// Let other readers get notified.
					readyReadFutures.offer(future);
				}
			} else {
				future = new DefaultReadFuture(this);
				getWaitingReadFutures().offer(future);
			}
		}

		return future;
	}

	public WriteFuture write(Object message, SocketAddress remoteAddress) {
		if (message == null) {
			throw new IllegalArgumentException(
					"Trying to write a null message : not allowed");
		}

		// We can't send a message to a connected session if we don't have
		// the remote address
		if (!getTransportMetadata().isConnectionless()
				&& (remoteAddress != null)) {
			throw new UnsupportedOperationException();
		}

		// If the session has been closed or is closing, we can't either
		// send a message to the remote side. We generate a future
		// containing an exception.
		if (isClosing() || !isConnected()) {
			WriteFuture future = new DefaultWriteFuture(this);
			WriteRequest request = new DefaultWriteRequest(message, future,
					remoteAddress);
			WriteException writeException = new WriteToClosedSessionException(
					request);
			future.setException(writeException);
			return future;
		}

		FileChannel openedFileChannel = null;

		// TODO: remove this code as soon as we use InputStream
		// instead of Object for the message.
		try {
			if ((message instanceof IoBuffer)
					&& !((IoBuffer) message).hasRemaining()) {
				// Nothing to write : probably an error in the user code
				throw new IllegalArgumentException(
						"message is empty. Forgot to call flip()?");
			} else if (message instanceof FileChannel) {
				FileChannel fileChannel = (FileChannel) message;
				message = new DefaultFileRegion(fileChannel, 0,
						fileChannel.size());
			} else if (message instanceof File) {
				File file = (File) message;
				openedFileChannel = new FileInputStream(file).getChannel();
				message = new FilenameFileRegion(file, openedFileChannel, 0,
						openedFileChannel.size());
			}
		} catch (IOException e) {
			ExceptionMonitor.getInstance().exceptionCaught(e);
			return DefaultWriteFuture.newNotWrittenFuture(this, e);
		}

		// Now, we can write the message. First, create a future
		WriteFuture writeFuture = new DefaultWriteFuture(this);
		WriteRequest writeRequest = new DefaultWriteRequest(message,
				writeFuture, remoteAddress);

		// Then, get the chain and inject the WriteRequest into it
		IoFilterChain filterChain = getFilterChain();
		filterChain.fireFilterWrite(writeRequest);

		// TODO : This is not our business ! The caller has created a
		// FileChannel,
		// he has to close it !
		if (openedFileChannel != null) {
			// If we opened a FileChannel, it needs to be closed when the write
			// has completed
			final FileChannel finalChannel = openedFileChannel;
			writeFuture.addListener(new IoFutureListener<WriteFuture>() {
				public void operationComplete(WriteFuture future) {
					try {
						finalChannel.close();
					} catch (IOException e) {
						ExceptionMonitor.getInstance().exceptionCaught(e);
					}
				}
			});
		}

		// Return the WriteFuture.
		return writeFuture;
	}

}
