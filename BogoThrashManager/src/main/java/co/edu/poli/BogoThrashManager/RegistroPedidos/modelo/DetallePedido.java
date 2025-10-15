package co.edu.poli.BogoThrashManager.RegistroPedidos.modelo;

import java.util.*;

import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.Producto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    private Long idDetalle;

    /**
     * 
     */
	
	@ElementCollection(fetch = FetchType.EAGER)  // Or a simple List if no complex associations needed
    @CollectionTable(name = "detalleProductos", joinColumns = @JoinColumn(name = "detalleId"))
    @Column(name = "productoId")
	private List<Long> productos;
   
    /**
     * 
     */
	@Column(name = "precioTotal")
    private Long precioTotal;

}