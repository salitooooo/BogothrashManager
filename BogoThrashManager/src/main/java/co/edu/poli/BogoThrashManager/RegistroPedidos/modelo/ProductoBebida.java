package co.edu.poli.BogoThrashManager.RegistroPedidos.modelo;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data  // Generates: getters, setters, toString(), equals(), hashCode() for all fields
@NoArgsConstructor  // Generates default (no-arg) constructor (required for JPA)
@AllArgsConstructor 
public class ProductoBebida extends Producto {


    /**
     * 
     */
    private TipoBebida tipo;

    /**
     * 
     */
    private boolean esCaliente;

    /**
     * 
     */
    private boolean tieneAlcohol;

}