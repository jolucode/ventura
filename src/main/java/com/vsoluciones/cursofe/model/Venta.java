package com.vsoluciones.cursofe.model;

import lombok.Data;

@Data
public class Venta {
    private String serie;
    private String numero;
    private String fecha_emision;
    private String hora_emision;
    private String fecha_vencimiento;
    private String moneda_id;
    private String forma_pago_id;
    private String total_gravada;
    private String total_igv;
    private String total_exonerada;
    private String total_inafecta;
    private String tipo_documento_codigo;
    private String nota;
    private String detraccion_porcentaje;
}
