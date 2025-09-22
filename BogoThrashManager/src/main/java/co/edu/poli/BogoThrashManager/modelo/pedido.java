package co.edu.poli.BogoThrashManager.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table(name="pedido")
public class pedido {
	
	@Id
	@GeneratedValue
	private String id;
	
	private String detalle;
	
	public pedido(String id, String detalle) {
		super();
		this.id = id;
		this.detalle = detalle;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
		return "pedido [id=" + id + ", detalle=" + detalle + "]";
	}
		

}
