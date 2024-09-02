package com.vsoluciones.cursofe.model;

import lombok.Data;

@Data
public class Empresa {
    private String ruc;
    private String razon_social;
    private String nombre_comercial;
    private String domicilio_fiscal;
    private String ubigeo;
    private String urbanizacion;
    private String distrito;
    private String provincia;
    private String departamento;
    private String modo;
    private String usu_secundario_produccion_user;
    private String usu_secundario_produccion_password;
}
