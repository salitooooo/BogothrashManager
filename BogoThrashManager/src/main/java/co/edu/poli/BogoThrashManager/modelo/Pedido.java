package co.edu.poli.BogoThrashManager.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Table(name="pedido")

@Data  // Generates: getters, setters, toString(), equals(), hashCode() for all fields
@NoArgsConstructor  // Generates default (no-arg) constructor (required for JPA)
@AllArgsConstructor 
public class Pedido {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment for Postgres
    private Long id;  // Changed to Long for simplicity
    
    @Column(name = "detallePedido")
    private DetallePedido detalle;
    
    @Column(name="cliente")
    private String cliente;
    
    @Column(name = "barista")
    private String barista;
    
    
    // Default constructor (required by JPA)
		

}
