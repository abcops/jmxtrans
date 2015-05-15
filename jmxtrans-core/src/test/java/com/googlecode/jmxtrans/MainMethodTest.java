/*
 * Copyright (c) 2009 - 2012, ABC, Sydney.
 * This program is the property of Australian Broadcasting Corporation.
 * All Rights Reserved. Use and distribution are limited by contract provisions.
 */
package com.googlecode.jmxtrans;

import com.google.common.collect.ImmutableList;
import com.googlecode.jmxtrans.exceptions.LifecycleException;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;

import static com.google.common.collect.ImmutableList.of;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author crysmi
 * @since <version>
 */
public class MainMethodTest {

  @Test(expected = java.io.FileNotFoundException.class )
  public void testAdditionalJars() throws Exception {
    String[] args = {"-j .","-a add.jar"};
    JmxTransformer.main(args);
  }
}
