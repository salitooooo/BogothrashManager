package co.edu.poli.BogoThrashManager.modelo;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Entity
@Table(name="DetallePedido")
@Data  // Generates: getters, setters, toString(), equals(), hashCode() for all fields
@NoArgsConstructor  // Generates default (no-arg) constructor (required for JPA)
@AllArgsConstructor 
public class DetallePedido {

  

    /**
     * 
     */
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name= "destallePedidoId")//foreing key en detalle pedido
    private long idDetalle;

    /**
     * 
     */
	@Column(name = "productos")
	private List<Producto> productos;
   
    /**
     * 
     */
	@Column(name = "precioTotal")
    private long precioTotal;

}