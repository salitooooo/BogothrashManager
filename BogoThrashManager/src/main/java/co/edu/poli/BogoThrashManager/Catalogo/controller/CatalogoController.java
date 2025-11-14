package co.edu.poli.BogoThrashManager.Catalogo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.BogoThrashManager.Catalogo.service.CatalogoService;
import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.Producto;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/catalogo")
public class CatalogoController {
	
	@PostMapping
	 public ResponseEntity<String> create()
	throws Exception{
		return ResponseEntity.ok("prodcuto insertado correctamente");
		
	}
	@GetMapping
	public ResponseEntity <String> getAll(){
		   return ResponseEntity.ok("lista de producto");
    }
	@GetMapping("/art/{nombre}")
	 public ResponseEntity<String> getByNombre(
	@Parameter(description = "Id del producto por buscar", required = true)
	@PathVariable String nombre){
		return ResponseEntity.ok("articulo");
		
	}
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(
	    	@Parameter(description = "Id del producto por eliminar", required = true)
	        @PathVariable Long id) {
		 	boolean deleted = true;
	        if (deleted) {
	            return ResponseEntity.noContent().build();  // 204 No Content on success
	        } else {
	            return ResponseEntity.notFound().build();  // 404 if not found
	        }
	    }

}
