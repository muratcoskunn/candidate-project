package com.sahabt.candidatepool.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper createModelMapper(){
        var modelMapper = new ModelMapper();
        return modelMapper;
    }
}
