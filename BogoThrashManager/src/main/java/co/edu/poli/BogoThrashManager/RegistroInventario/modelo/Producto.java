package co.edu.poli.BogoThrashManager.RegistroInventario.modelo;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */

@Entity
@Table(name="Producto")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
use = JsonTypeInfo.Id.NAME, 
include = JsonTypeInfo.As.PROPERTY, 
property = "tipoProducto")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ProductoSnack.class, name = "snack"),
    @JsonSubTypes.Type(value = ProductoBebida.class, name = "bebida")
})


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