package com.vsoluciones.model;

import lombok.Data;

import java.util.List;

@Data
public class Factura {
    private Empresa empresa;
    private Cliente cliente;
    private Venta venta;
    private List<Item> items;
    private List<Cuota> cuotas;
}
