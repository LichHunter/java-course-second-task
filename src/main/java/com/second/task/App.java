package com.second.task;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Hello world!
 */
public class App {
	private static Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("Starting application");

		PropertiesReader reader;
		int min = 0;
		int max = 0;
		int step = 0;

		try {
			reader = new PropertiesReader(new FileInputStream("./global.properties"));
			min = Integer.parseInt(reader.getProperty("min"));
			max = Integer.parseInt(reader.getProperty("max"));
			step = Integer.parseInt(reader.getProperty("step"));

		} catch (IOException e) {
			logger.error("No global properties found", e);
			try {
				logger.info("Trying to get properties from app.properties");

				reader = new PropertiesReader("app.properties");
				min = Integer.parseInt(reader.getProperty("min"));
				max = Integer.parseInt(reader.getProperty("max"));
				step = Integer.parseInt(reader.getProperty("step"));

			} catch (IOException e1) {
				logger.error("Failed to load properties from app.properties file", e);
			}
		} catch (NumberFormatException e) {
			logger.error("In max and min properties must be numbers", e);
		}

		logger.info("Starting output of multiplication table");

		for (int i = min; i <= max; i += step) {
			for (int j = min; j <= max; j += step) {
				logger.debug(String.format("%d*%d=%d", i, j, i * j));
			}
		}
	}

	private static class PropertiesReader {
		private final Properties properties;

		public PropertiesReader(String propertyFileName) throws IOException {
			InputStream is = getClass().getClassLoader()
					.getResourceAsStream(propertyFileName);
			this.properties = new Properties();
			this.properties.load(is);
		}

		public PropertiesReader(FileInputStream fileInputStream) throws IOException {
			this.properties = new Properties();
			this.properties.load(fileInputStream);
		}

		public String getProperty(String propertyName) {
			return this.properties.getProperty(propertyName);
		}
	}
}

