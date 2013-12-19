package design_pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestMain {

	public static void main(String[] args) {
		Subject sub = new ProxySubject();
		sub.request();

		RealSubject rs = new RealSubject();
		InvocationHandler ds = new DynamicSubject(rs);
		Class<? extends Subject> cls = rs.getClass();

		// /////////////////////////////////////////////////////////
		Subject subject = (Subject) Proxy.newProxyInstance(
				cls.getClassLoader(), cls.getInterfaces(), ds);
		subject.request();

		/*
		 * Equals above
		 * ******************************************************************
		 * Class c = Proxy .getProxyClass(cls.getClassLoader(),
		 * cls.getInterfaces()); Constructor ct = c .getConstructor(new Class[]
		 * { InvocationHandler.class }); Subject subject = (Subject)
		 * ct.newInstance(new Object[] { ds });
		 */
	}
}
