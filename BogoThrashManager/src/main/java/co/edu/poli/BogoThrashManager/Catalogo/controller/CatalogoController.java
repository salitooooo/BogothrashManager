package co.edu.poli.BogoThrashManager.Catalogo.controller;

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

import co.edu.poli.BogoThrashManager.Catalogo.modelo.Articulo;
import co.edu.poli.BogoThrashManager.Catalogo.service.CatalogoService;
import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.Producto;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/catalogo")
public class CatalogoController {
	
	@Autowired
	private CatalogoService catalogoService;
	
	
	@PostMapping
	 public ResponseEntity<Articulo> create(@RequestBody Producto dto)
	throws Exception{
		return ResponseEntity.ok(catalogoService.createArticulo(dto));
		
	}
	@GetMapping
	public ResponseEntity <List<Articulo>> getAll(){
		   return ResponseEntity.ok(catalogoService.getAllArticulos());
    }
	@GetMapping("/{categoria}")
	public ResponseEntity <List<Articulo>> getAllCategoria(@PathVariable String categoria){
		   return ResponseEntity.ok(catalogoService.getAllByCategoria(categoria));
    }
	@GetMapping("/{nombre}")
	 public ResponseEntity<Articulo> getByNombre(
	@Parameter(description = "Id del producto por buscar", required = true)
	@PathVariable String nombre){
		return ResponseEntity.ok(catalogoService.getArticuloByNombre(nombre));
		
	}
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(
	    	@Parameter(description = "Id del producto por eliminar", required = true)
	        @PathVariable Long id) {
		 	boolean deleted = catalogoService.deleteArticulo(id);
	        if (deleted) {
	            return ResponseEntity.noContent().build();  // 204 No Content on success
	        } else {
	            return ResponseEntity.notFound().build();  // 404 if not found
	        }
	    }

}
