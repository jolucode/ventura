package com.vsoluciones.cursofe.model;

import lombok.Data;

@Data
public class Item {
    private String producto;
    private String cantidad;
    private String precio_base;
    private String codigo_sunat;
    private String codigo_producto;
    private String codigo_unidad;
    private String tipo_igv_codigo;
}
