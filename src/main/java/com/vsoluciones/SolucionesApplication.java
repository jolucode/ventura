package com.vsoluciones;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SolucionesApplication {

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(SolucionesApplication.class, args);
    }
}
