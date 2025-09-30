package co.edu.poli.BogoThrashManager.Controller;
import co.edu.poli.BogoThrashManager.modelo.Pedido;
import co.edu.poli.BogoThrashManager.service.PedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@Api(value = "pedidos", tags = "pedido")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
@Autowired
private PedidoService pedidoService;

@ApiOperation(value = "Crea un pedido", notes = "Crea un pedido con todos sus datos")
@ApiResponses(value = {
		@ApiResponse(code = 200, message = "operacion exitosa"),
	    @ApiResponse(code = 404, message = "no se pudo crear el pedido")
})
@PostMapping
public ResponseEntity create(
		@ApiParam(value = "Nuevo pedido del cliente", required = true)
		@RequestBody Pedido pedido) {
Pedido saved = pedidoService.createPedido(pedido);
return ResponseEntity.ok(saved);
}

@ApiOperation(value = "Consigue todos los pedidos")
@ApiResponses(value = {
	      @ApiResponse(code = 200, message = "operacion exitosa"),
	      @ApiResponse(code = 404, message = "no se pudo encontrar los pedidos o no existen")
	  })
@GetMapping
public ResponseEntity<List> getAll() {
return ResponseEntity.ok(pedidoService.getAllPedidos());
}

@ApiOperation(value = "Encuentra un pedido por id", notes = "A travez de una id, busca un pedido en la base de datos")
@ApiResponses(value = {
		@ApiResponse(code = 200, message = "operacion exitosa"),
	    @ApiResponse(code = 404, message = "no se pudo encontrar el pedido o no existe")
})
@GetMapping("/{id}")
public ResponseEntity<Pedido> getById(
		@ApiParam(value = "Id del pedido por buscar", required = true)
		@PathVariable Long id) {
    Optional<Pedido> pedido = pedidoService.getPedidoById(id);
    return pedido.map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
}

@ApiOperation(value = "Modifica un pedido por id", notes = "Crea una copia del pedido con la id, permitiendo modificarlo mas tarde")
@ApiResponses(value = {
		@ApiResponse(code = 200, message = "operacion exitosa"),
	    @ApiResponse(code = 404, message = "no se pudo encontrar el pedido o no existe")
})
@PutMapping("/{id}")
public ResponseEntity<Pedido> update(
		@ApiParam(value = "Id del pedido a cambiar", required = true)
		@PathVariable Long id, 
		@ApiParam(value = "Pedido ya modificado", required = true)
		@RequestBody Pedido updatedPedido) {
    Optional<Pedido> updated = pedidoService.updatePedido(id, updatedPedido);
    return updated.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());  // 404 if not found
}

@ApiOperation(value = "Elimina un pedido", notes = "Elimina un pedido de la base de datos utilizando su id")
@ApiResponses(value = {
		@ApiResponse(code = 200, message = "operacion exitosa"),
	    @ApiResponse(code = 404, message = "no se pudo encontrar el pedido o no existe")
})
@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(
		@ApiParam(value = "Id del pedido a eliminar")
		@PathVariable Long id) {
    boolean deleted = pedidoService.deletePedido(id);
    if (deleted) {
        return ResponseEntity.noContent().build();  // 204 No Content on success
    } else {
        return ResponseEntity.notFound().build();  // 404 if not found
    }
}
}
