package com.ro8.varastosofta.application;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

/**
 * Kielitiedoston lataaminen.
 * @author Riina Antikainen
 * @author Tuukka Mytty
 * @author Janne Valle
 */
public class UTF8Control extends Control {
	
	@Override
	public ResourceBundle newBundle (String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException {
		String bundleName = toBundleName(baseName, locale);
		String resourceName = toResourceName(bundleName, "properties");
		ResourceBundle bundle = null;
		if (reload) {
			URL url = loader.getResource(resourceName);
			if (url != null) {
				URLConnection connection = url.openConnection();
				if (connection != null) {
					connection.setUseCaches(false);
					try (InputStream stream = connection.getInputStream()) {
						if (stream != null) {
							bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
						}
					} catch (Exception e) {
						return bundle;
					}
				}
			}
		} else {
			try (InputStream stream = loader.getResourceAsStream(resourceName)) {
				if (stream != null) {
					bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
				}
			} catch (Exception e) {
				return bundle;
			}
		}
		return bundle;
	}
}
