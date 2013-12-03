package ru.cti.tve.rest.serviceutil;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;


@Service("configurator")
public class Configurator {
	
	@Autowired
	@Qualifier("config")
	private MessageSource config;
	
	private Locale defaultLocale = null;
	private String defaultLocaleString = null;
	
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
	
	public Locale getDefaultLocale(){
		if(defaultLocale == null){
			String locale = getDefaultLocaleString();
			defaultLocale = new Locale(locale.substring(0,2), locale.substring(3,5));
		}
		return defaultLocale;
	}
	
	public String getDefaultLocaleString(){
		if(defaultLocaleString == null){
			defaultLocaleString = getParamFromConfig("tve.defaultLocale"); 
		}
		return defaultLocaleString;
	}
	
}
