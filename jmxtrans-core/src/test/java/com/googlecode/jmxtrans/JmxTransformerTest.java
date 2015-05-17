package com.googlecode.jmxtrans;

import java.net.URL;

import com.googlecode.jmxtrans.classloader.ClassLoaderEnricher;
import com.googlecode.jmxtrans.cli.CliArgumentParser;
import com.googlecode.jmxtrans.cli.JmxTransConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class JmxTransformerTest {


    @Test(expected = java.io.FileNotFoundException.class )
    public void testAdditionalJarMissing() throws Exception {
      String[] args = {"-j", ".","-a", "nonexistant.jar"};
      JmxTransConfiguration configuration = new CliArgumentParser().parseOptions(args);
      JmxTransformer.enrichClassPath(configuration);
    }

    @Test
    public void testAdditionalJar() throws Exception {
    	String filepath = "/dummy.jar.local";
    	URL jarurl = JmxTransformerTest.class.getResource(filepath);
    	String[] args = {"-j", ".","-a", jarurl.getPath() };
    	JmxTransConfiguration configuration = new CliArgumentParser().parseOptions(args);
    	JmxTransformer.enrichClassPath(configuration);
    	assertThat(Class.forName("dummy.Dummy")!=null);
    }

    @Test
    public void testNoAdditionalJar() throws Exception {
      String[] args = {"-j", "."};
      JmxTransConfiguration configuration = new CliArgumentParser().parseOptions(args);
      JmxTransformer.enrichClassPath(configuration);
    }

}
