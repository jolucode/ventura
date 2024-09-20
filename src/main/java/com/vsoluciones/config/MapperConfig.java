package com.vsoluciones.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class MapperConfig {

  @Bean("defaultMapper")
  public ModelMapper defaultMapper() {
    return new ModelMapper();
  }



}
