package com.vsoluciones;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vsoluciones.cursofe.ServicePrueva;
import com.vsoluciones.cursofe.model.Factura;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class SolucionesApplication {

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(SolucionesApplication.class, args);
    }
}
