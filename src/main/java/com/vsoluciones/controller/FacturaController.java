package com.vsoluciones.controller;

import com.vsoluciones.model.Factura;
import com.vsoluciones.service.FacturaService;
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
    public StringBuilder procesarFactura(@RequestBody Factura factura) {
        return facturaService.procesarFactura(factura);
    }
}
