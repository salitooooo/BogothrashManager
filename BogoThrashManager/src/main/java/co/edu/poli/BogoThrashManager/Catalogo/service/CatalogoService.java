package co.edu.poli.BogoThrashManager.Catalogo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.poli.BogoThrashManager.Catalogo.modelo.Articulo;
import co.edu.poli.BogoThrashManager.Catalogo.repository.CatalogoRepository;
import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.Producto;
import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.ProductoBebida;
import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.ProductoSnack;
import co.edu.poli.BogoThrashManager.RegistroPedidos.dto.PedidoInsertDto;
import co.edu.poli.BogoThrashManager.RegistroPedidos.modelo.Pedido;
import lombok.Data;

@Service
public class CatalogoService {
	@Autowired
	private CatalogoRepository catalogorepository;
	
	public Articulo createArticulo(Producto dto) throws Exception{
		if(dto instanceof ProductoSnack) {
			return catalogorepository.findByNombre(dto.getNombre()).orElseGet(() -> {
	            // Create and save new if not found
				Articulo newArt = new Articulo();
	            newArt.setNombre(dto.getNombre());
	            newArt.setPrecio(dto.getPrecio());
	            newArt.setTipo("Snack");
	            
	            ProductoSnack ps = (ProductoSnack) dto;
	            System.out.println(ps);
	            String categoria = "";
	            if(ps.isEsDulce()) {
	            	categoria += "dulce";
	            }else {
	            	categoria += "salado";
	            }
	            if(ps.isEsVegano()) {
	            	categoria += "-vegano";
	            }else {
	            	categoria += "-novegano";
	            }
	            newArt.setCategoria(categoria);
	            return catalogorepository.save(newArt);
	        });
		}else {
			return catalogorepository.findByNombre(dto.getNombre()).orElseGet(() -> {
	            // Create and save new if not found
				Articulo newArt = new Articulo();
	            newArt.setNombre(dto.getNombre());
	            newArt.setPrecio(dto.getPrecio());
	            newArt.setTipo("Bebida");
	            
	            ProductoBebida ps = (ProductoBebida) dto;
	            String categoria = "";
	            if(ps.isEsCaliente()) {
	            	categoria += "caliente";
	            }else {
	            	categoria += "frio";
	            }
	            if(ps.isTieneAlcohol()) {
	            	categoria += "-alcohol";
	            }else {
	            	categoria += "-noalcohol";
	            }
	            newArt.setCategoria(categoria);
	            return catalogorepository.save(newArt);
	        });
		}
		
	}
	
	public List<Articulo> getAllArticulos() {
		return catalogorepository.findAll();
	}
	
	public List<Articulo> getAllByCategoria(String categoria){
		return catalogorepository.findAllByCategoria(categoria);
	}
	
	public boolean deleteArticulo(Long id) {
		if(catalogorepository.existsById(id)) {
			catalogorepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public Articulo getArticuloByNombre(String nombre) {
		return catalogorepository.findByNombre(nombre).orElse(null);
	}

}
