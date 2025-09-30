package co.edu.poli.BogoThrashManager.Notificaciones.modelo;

import java.util.*;

import co.edu.poli.BogoThrashManager.RegistroPedidos.modelo.DetallePedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data  // Generates: getters, setters, toString(), equals(), hashCode() for all fields
@NoArgsConstructor  // Generates default (no-arg) constructor (required for JPA)
@AllArgsConstructor 

public class Pedido {


    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String detallePedido;

    /**
     * 
     */
    private String cliente;

    /**
     * 
     */
    private String barista;

    /**
     * 
     */
    private String fechaCompra;

    /**
     * 
     */
    private boolean cupon;

    /**
     * 
     */
    private String formaDePago;

}