package com.rhysticlibrary.core;

import com.rhysticlibrary.core.config.TestApplicationConfig;
import com.rhysticlibrary.core.generators.common.TimestampGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = TestApplicationConfig.class)
@SpringBootTest
public abstract class BaseComponent {

  @Autowired
  protected TimestampGenerator timestampGenerator;
}
