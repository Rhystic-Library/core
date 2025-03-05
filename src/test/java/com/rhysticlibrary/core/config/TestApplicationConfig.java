package com.rhysticlibrary.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rhysticlibrary.core.modules.modelmapper.InstantModule;
import com.rhysticlibrary.core.serializers.objectmapper.InstantSerializer;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;

@ComponentScan(basePackages = "com.rhysticlibrary")
@Configuration
public class TestApplicationConfig {

  @Bean
  public ModelMapper modelMapper(InstantModule instantModule) {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
        .setDeepCopyEnabled(true)
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
    modelMapper.registerModule(instantModule);

    return modelMapper;
  }

  @Bean
  public ObjectMapper objectMapper(InstantSerializer instantSerializer) {
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addSerializer(Instant.class, instantSerializer);
    return new ObjectMapper()
        .registerModule(javaTimeModule);
  }
}
