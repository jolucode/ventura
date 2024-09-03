package com.vsoluciones.utils;

import java.util.HashMap;
import java.util.Map;

public class UtilMoneda {

    private static final Map<String, String> CODIGO_MONEDA_MAP = new HashMap<>();

    static {
        CODIGO_MONEDA_MAP.put("1", "PEN");
        CODIGO_MONEDA_MAP.put("2", "USD");
    }

    /**
     * Devuelve el nombre de la moneda basado en el código.
     *
     * @param codigo El código de moneda (por ejemplo, "01" o "02").
     * @return El nombre de la moneda correspondiente.
     */
    public static String obtenerMonedaPorCodigo(String codigo) {
        return CODIGO_MONEDA_MAP.getOrDefault(codigo, "Código de moneda no válido");
    }

    private static final double FACTOR_IGV = 1.18;

    public static String multiplicarPrecio(String precioBase) {
        try {
            // Convertir el precio base de String a double
            double precio = Double.parseDouble(precioBase);

            // Multiplicar el precio por el factor constante
            double resultado = precio * FACTOR_IGV;

            // Convertir el resultado de vuelta a String y formatear con 2 decimales
            return String.format("%.2f", resultado);
        } catch (NumberFormatException e) {
            // Manejar el caso en que el String no se puede convertir a número
            System.err.println("El precio base no es un número válido: " + e.getMessage());
            return "Error";
        }
    }

    private static final double FACTOR = 0.18;

    public static String obtenerIgvPrecioUnitPorCantidad(String precioBase, String cantidad) {
        try {
            // Convertir los Strings a double
            double precio = Double.parseDouble(precioBase);
            double cantidadNumerica = Double.parseDouble(cantidad);

            // Multiplicar el precio por la cantidad
            double resultado = precio * cantidadNumerica * FACTOR;

            // Convertir el resultado a String y formatear con 2 decimales
            return String.format("%.2f", resultado);
        } catch (NumberFormatException e) {
            // Manejar el caso en que uno de los Strings no se puede convertir a número
            System.err.println("Uno de los valores no es un número válido: " + e.getMessage());
            return "Error";
        }
    }

    public static String multiplicarPrecioYCantidad(String precioBase, String cantidad) {
        try {
            // Convertir los Strings a double
            double precio = Double.parseDouble(precioBase);
            double cantidadNumerica = Double.parseDouble(cantidad);

            // Multiplicar el precio por la cantidad
            double resultado = precio * cantidadNumerica;

            // Convertir el resultado a String y formatear con 2 decimales
            return String.format("%.2f", resultado);
        } catch (NumberFormatException e) {
            // Manejar el caso en que uno de los Strings no se puede convertir a número
            System.err.println("Uno de los valores no es un número válido: " + e.getMessage());
            return "Error";
        }
    }


    public static boolean isNullOrTrimmedEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
