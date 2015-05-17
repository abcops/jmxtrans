package com.googlecode.jmxtrans.classloader;

import org.junit.Test;

/**
 * @author crysmi
 * @since <version>
 */
public class EnricherTest {

    @Test
    public void testSLF4JLog4jImplJar() throws Exception {
      LoggingLoader.enrichWithLog4J();
      Object log4jLoggerFactory = Class.forName("org.slf4j.impl.Log4jLoggerFactory").newInstance();
      if(log4jLoggerFactory == null) {
    	  throw new NullPointerException("slf4j-log4j not loaded");
      }
    }

    @Test
    public void testSLF4JLogbackImplJars() throws Exception {
      LoggingLoader.enrichWithLogback();
      Object logEncoder = Class.forName("ch.qos.logback.classic.encoder.PatternLayoutEncoder").newInstance();
      if(logEncoder == null) {
    	  throw new NullPointerException("logback-classic not loaded");
      }
    }
}
