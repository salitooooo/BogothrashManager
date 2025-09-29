package co.edu.poli.BogoThrashManager.modelo;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductoSnack extends Producto {


    /**
     * 
     */
    private TipoSnack tipo;

    /**
     * 
     */
    private boolean esDulce;

    /**
     * 
     */
    private boolean esVegano;

}