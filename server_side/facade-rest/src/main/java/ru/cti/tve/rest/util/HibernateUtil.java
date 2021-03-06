package ru.cti.tve.rest.util;

import org.hibernate.proxy.HibernateProxy;

public class HibernateUtil {
	
	public static <T> T deproxy(final Object maybeProxy, final Class < T > baseClass) throws ClassCastException {
        if (maybeProxy instanceof HibernateProxy) {
            return baseClass.cast(((HibernateProxy) maybeProxy).getHibernateLazyInitializer().getImplementation());
        } else {
            return baseClass.cast(maybeProxy);
        }
    }
	
}
