package ru.cti.tve.rest;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;


@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public abstract class BaseTest extends AbstractTransactionalTestNGSpringContextTests{
	
	@Autowired
	@Qualifier("config")
	protected MessageSource config;
	
	private Locale defaultLocale = null;
	
	
	protected String getParamFromConfig(String paramName) {
        if (config == null) {
            //LoggerUtilBean.logWarn(log, "Error during getting parameter '" + paramName + "' ! (iptvmwConfig is NULL)");
            return null;
        }
        try {
            return config.getMessage(paramName, null, null, new Locale("ru", "RU"));
        } catch (Exception e) {
            //LoggerUtilBean.logWarn(log, "Error during getting parameter '" + paramName + "' !", e);
            return null;
        }

    }
	
	protected Locale getDefaultLocale(){
		if(defaultLocale == null){
			String locale = getParamFromConfig("tve.defaultLocale");
			defaultLocale = new Locale(locale.substring(0,2), locale.substring(3,5));
		}
		return defaultLocale;
	}
}
