package co.edu.poli.BogoThrashManager.RegistroInventario.modelo;

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
@Table(name = "ProductoSnack")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductoSnack extends Producto {


    /**
     * 
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TipoSnackId", referencedColumnName = "id")
    private TipoSnack tipo;

    /**
     * 
     */
	@Column(name = "EsDulce")
    private boolean esDulce;

    /**
     * 
     */
	@Column(name = "EsVegano")
    private boolean esVegano;

}