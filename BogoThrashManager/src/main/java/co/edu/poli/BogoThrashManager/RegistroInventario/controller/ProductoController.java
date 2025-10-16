package co.edu.poli.BogoThrashManager.RegistroInventario.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.Producto;
import co.edu.poli.BogoThrashManager.RegistroInventario.service.ProductoService;
import co.edu.poli.BogoThrashManager.RegistroPedidos.modelo.Pedido;
import io.swagger.v3.oas.annotations.Parameter;



@RestController
public class ProductoController {
	@Autowired
	private ProductoService productoService;
	
	@PostMapping
	 public ResponseEntity<Producto> create(
	@RequestBody Producto producto) throws Exception{
		return ResponseEntity.ok(productoService.findOrCreateProducto(producto));
		
	}
	@GetMapping
	public ResponseEntity<List<Producto>> getAll(){
		   return ResponseEntity.ok(productoService.getAllProductos());
    }
	@GetMapping("/{id}")
	 public ResponseEntity<Optional<Producto>> getById(
	@PathVariable Long id){
		return ResponseEntity.ok(productoService.getProductoById(id));
		
	}
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(
	        @PathVariable Long id) {
	        boolean deleted = productoService.deleteProducto(id);
	        if (deleted) {
	            return ResponseEntity.noContent().build();  // 204 No Content on success
	        } else {
	            return ResponseEntity.notFound().build();  // 404 if not found
	        }
	    }


}
