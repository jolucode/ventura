package com.vsoluciones.model;

import lombok.Data;

@Data
public class Cliente {
    private String razon_social_nombres;
    private String numero_documento;
    private String codigo_tipo_entidad;
    private String cliente_direccion;
}
