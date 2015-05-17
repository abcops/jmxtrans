package com.googlecode.jmxtrans.classloader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author crysmi
 * @since <version>
 */
public class EnricherFailTest {

	String log4jstring;
	String logbackstring;

	@Before
	public void saveProperties() {
		log4jstring = System.getProperty("outputwriters.log4j.jarpath");
		logbackstring = System.getProperty("outputwriters.logback.jarpath");
	      System.setProperty("outputwriters.log4j.jarpath", "nonexistant.jar");
	      System.setProperty("outputwriters.logback.jarpath", "nonexistant.jar");
	}

    @Test(expected = ClassNotFoundException.class)
    public void testNoSLF4JLog4jImplJar() throws Exception {
      LoggingLoader.enrichWithLog4J();
      Object log4jLoggerFactory = Class.forName("org.slf4j.impl.Log4jLoggerFactory").newInstance();
    }

    @Test(expected = ClassNotFoundException.class)
    public void testNoSLF4JLogbackImplJars() throws Exception {
      LoggingLoader.enrichWithLogback();
      Object logEncoder = Class.forName("ch.qos.logback.classic.encoder.PatternLayoutEncoder").newInstance();
    }

    @Test(expected = java.io.FileNotFoundException.class )
    public void testAdditionalJarMissing() throws Exception {
      String args = "/nonexistant.jar";
      LoggingLoader.enrichWithResourceFile(args);
    }

	@After
	public void restoreProperties() {
	      System.setProperty("outputwriters.log4j.jarpath", log4jstring);
	      System.setProperty("outputwriters.logback.jarpath", logbackstring);
	}
}
