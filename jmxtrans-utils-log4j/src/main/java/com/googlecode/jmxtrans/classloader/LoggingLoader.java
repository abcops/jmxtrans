package com.googlecode.jmxtrans.classloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingLoader {


	private static final Logger log = LoggerFactory.getLogger(LoggingLoader.class);

	public static void enrichWithLog4J() throws MalformedURLException, URISyntaxException {
		String filepath = System.getProperty("outputwriters.log4j.jarpath");

		if(StringUtils.isNotEmpty(filepath)) {
			try {
				enrichWithResourceFile(filepath);
			} catch (FileNotFoundException e) {
				log.warn("slf4j-log4j12 jar not found at " + filepath);
				log.warn("Without slf4j-log4j12 at this location, the following output writers will not work: KeyOutWriter Log4JWriter DailyKeyOutWriter NagiosWriter");
			}
		} else {
			log.warn("system property outputwriters.log4j.jarpath not defined");
			log.warn("Without this property set to the filepath of a slf4j-log4j12 jar, the following output writers will not work: KeyOutWriter Log4JWriter DailyKeyOutWriter NagiosWriter");
		}
	}

	public static void enrichWithLogback() throws URISyntaxException, MalformedURLException {
		String filepath = System.getProperty("outputwriters.logback.jarpath");
		log.debug("outputwriters.logback.jarpath defined as " + filepath);
		if(StringUtils.isNotEmpty(filepath)) {

			try {
				enrichWithResourceFile(filepath);
			} catch (FileNotFoundException e) {
				log.warn("logback-classic jar not found at " + filepath);
				log.warn("Without logback-classic at this location, the following output writers will not work: TimeBasedRollingKeyOutWriter");
			}
		} else {
			log.warn("system property outputwriters.logback.jarpath not defined");
			log.warn("Without this property set to the filepath of a logback-classic jar, the following output writers will not work: TimeBasedRollingKeyOutWriter");
		}
	}

	public static void enrichWithResourceFile(String filepath) throws URISyntaxException, MalformedURLException, FileNotFoundException {
		ClassLoaderEnricher enricher = new ClassLoaderEnricher();
		URL jarurl = ClassLoaderEnricher.class.getResource(filepath);
		if(jarurl!=null) {
			File jarfile = new File(jarurl.toURI());
			enricher.add(jarfile);
		} else {
			throw new FileNotFoundException("no such enriching file: " + filepath);
		}
	}
	
}
