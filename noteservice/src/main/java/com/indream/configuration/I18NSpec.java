/**
 * 
 */
package com.indream.configuration;

import java.util.Locale;

/**
 * @author Akshay
 *
 */
public interface I18NSpec {
	public void setLocale(Locale locale);
	public String getMessage(String message);
	
}
