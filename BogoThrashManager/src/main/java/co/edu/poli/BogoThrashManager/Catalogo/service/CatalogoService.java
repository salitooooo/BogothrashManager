package co.edu.poli.BogoThrashManager.Catalogo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.poli.BogoThrashManager.Catalogo.modelo.Articulo;
import co.edu.poli.BogoThrashManager.Catalogo.repository.CatalogoRepository;
import co.edu.poli.BogoThrashManager.RegistroPedidos.dto.PedidoInsertDto;
import co.edu.poli.BogoThrashManager.RegistroPedidos.modelo.Pedido;
import lombok.Data;


public class CatalogoService {
	@Autowired
	private CatalogoRepository catalogorepository;
	
	public Articulo createArticulo(PedidoInsertDto dto) throws Exception{
		return null;
	}
	
	public List<Articulo> getAllArticulos() {
		return null;
	}

	public boolean deleteArticulo(Long id) {
		return true;
	}
	
	public Articulo getArticuloByNombre(String nombre) {
		return null;
	}

}
