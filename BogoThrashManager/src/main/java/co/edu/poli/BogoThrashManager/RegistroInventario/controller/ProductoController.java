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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "inventario", description = "Operaciones relacionadas con productos y manejo de inventario.")
@RestController
@RequestMapping("/api/inventarios")
public class ProductoController {
	@Autowired
	private ProductoService productoService;
	
    @Operation(summary = "Crea un producto", description = "Crea un pedido con todos sus datos y cantidad en inventario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "No se pudo crear el producto")
    })
	@PostMapping
	 public ResponseEntity<Producto> create(
	@Parameter(description = "Detalles del producto", required = true)
	@RequestBody Producto producto) throws Exception{
		return ResponseEntity.ok(productoService.findOrCreateProducto(producto));
		
	}
    @Operation(summary = "Consigue inventario", description = "Consigue todos los productos que se encuentran en inventario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "No se pudo encontrar el inventario")
    })
	@GetMapping
	public ResponseEntity<List<Producto>> getAll(){
		   return ResponseEntity.ok(productoService.getAllProductos());
    }
    @Operation(summary = "Consigue un producto", description = "Consigue un producto en inventario y muestra sus detalles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "No se pudo encotrar el producto solicitado")
    })
	@GetMapping("/{id}")
	 public ResponseEntity<Optional<Producto>> getById(
	@Parameter(description = "Id del producto por buscar", required = true)
	@PathVariable Long id){
		return ResponseEntity.ok(productoService.getProductoById(id));
		
	}
    @Operation(summary = "Elimina un producto", description = "Consigue un producto en inventario y lo elimina de la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "No se pudo encotrar el producto solicitado")
    })
	 @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(
	    	@Parameter(description = "Id del producto por eliminar", required = true)
	        @PathVariable Long id) {
	        boolean deleted = productoService.deleteProducto(id);
	        if (deleted) {
	            return ResponseEntity.noContent().build();  // 204 No Content on success
	        } else {
	            return ResponseEntity.notFound().build();  // 404 if not found
	        }
	    }


}
