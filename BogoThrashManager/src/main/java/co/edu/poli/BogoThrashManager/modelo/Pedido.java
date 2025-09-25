package co.edu.poli.BogoThrashManager.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table(name="pedido")
public class Pedido {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment for Postgres
    private Long id;  // Changed to Long for simplicity
    private String detalle;
    // Default constructor (required by JPA)
    public Pedido() {}
    // Parameterized constructor (optional, for convenience)
    public Pedido(String detalle) {
        this.detalle = detalle;
    }
    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDetalle() {
        return detalle;
    }
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    @Override
    public String toString() {
        return "Pedido [id=" + id + ", detalle=" + detalle + "]";
    }
		

}
