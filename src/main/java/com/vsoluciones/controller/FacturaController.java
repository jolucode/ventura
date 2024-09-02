package com.vsoluciones.controller;

import com.vsoluciones.cursofe.model.Factura;
import com.vsoluciones.service.impl.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping
    public String procesarFactura(@RequestBody Factura factura) {
        // Llamar al servicio para procesar la factura
        facturaService.procesarFactura(factura);
        return "Factura procesada con éxito";
    }
}
