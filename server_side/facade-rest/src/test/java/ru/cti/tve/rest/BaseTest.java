package ru.cti.tve.rest;

import java.util.Locale;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;


@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public abstract class BaseTest extends AbstractTransactionalTestNGSpringContextTests{
	
		
	private Locale defaultLocale = null;
	
	
		
	protected Locale getDefaultLocale(){
		if(defaultLocale == null){
			String locale = "ru_RU";
			defaultLocale = new Locale(locale.substring(0,2), locale.substring(3,5));
		}
		return defaultLocale;
	}
}
