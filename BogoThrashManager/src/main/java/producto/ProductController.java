package producto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	@PostMapping
	public String createProducto() {
		return "producto Creado";
		//tt
		
	}
	
	@GetMapping
	public String getProducto() {
		return "producto obtenido";
		
	}
	
	@PutMapping
	public String updateProducto() {
		return "producto Actualizado";
		
	}
	
	@DeleteMapping
	public String deleteProducto() {
		return "producto eliminado";
		
	}

}
