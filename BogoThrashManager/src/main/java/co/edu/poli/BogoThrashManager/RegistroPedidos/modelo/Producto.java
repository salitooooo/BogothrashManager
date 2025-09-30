package co.edu.poli.BogoThrashManager.RegistroPedidos.modelo;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */

@Entity
@Table(name="Producto")
@Data  // Generates: getters, setters, toString(), equals(), hashCode() for all fields
@NoArgsConstructor  // Generates default (no-arg) constructor (required for JPA)
@AllArgsConstructor 
public abstract class Producto {
//xd
    /**
     * 
     */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long idProducto;

    /**
     * 
     */
	@Column(name="nombre")
    private String nombre;

    /**
     * 
     */
	@Column(name="precio")
    private long precio;

}