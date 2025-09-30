package co.edu.poli.BogoThrashManager.RegistroPedidos.modelo;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Entity
@Table(name = "ProductoBebida")
@Data  // Generates: getters, setters, toString(), equals(), hashCode() for all fields
@NoArgsConstructor  // Generates default (no-arg) constructor (required for JPA)
@AllArgsConstructor 
public class ProductoBebida extends Producto {


    /**
     * 
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TipoBebidaId", referencedColumnName = "id")
    private TipoBebida tipo;

    /**
     * 
     */
	@Column(name = "EsCaliente")
    private boolean esCaliente;

    /**
     * 
     */
	@Column(name = "TieneAlcohol")
    private boolean tieneAlcohol;

}