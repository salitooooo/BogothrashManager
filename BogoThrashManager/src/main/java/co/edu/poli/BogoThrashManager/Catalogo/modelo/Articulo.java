package co.edu.poli.BogoThrashManager.Catalogo.modelo;

import co.edu.poli.BogoThrashManager.Catalogo.service.CatalogoService;
import jakarta.annotation.Generated;
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
@Table(name= "catalogo")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Articulo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Nombre")
	private String nombre;
	
	@Column(name = "Tipo")
	private String tipo;
	
	@Column (name = "Categoria")
	private String categoria;
	
	@Column(name = "precio")
	private Long precio;
	
	

}
