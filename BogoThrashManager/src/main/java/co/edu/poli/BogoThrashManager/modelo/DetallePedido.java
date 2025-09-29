package co.edu.poli.BogoThrashManager.modelo;

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
public class DetallePedido {

  

    /**
     * 
     */
    private long idDetalle;

    /**
     * 
     */
    private List<Producto> productos;

    /**
     * 
     */
    private long precioTotal;

}