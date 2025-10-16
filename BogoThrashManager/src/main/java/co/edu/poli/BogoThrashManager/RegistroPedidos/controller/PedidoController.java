package co.edu.poli.BogoThrashManager.RegistroPedidos.controller;

import co.edu.poli.BogoThrashManager.Notificaciones.modelo.Notificacion;
import co.edu.poli.BogoThrashManager.Notificaciones.service.NotificacionService;
import co.edu.poli.BogoThrashManager.RegistroPedidos.dto.PedidoInsertDto;
import co.edu.poli.BogoThrashManager.RegistroPedidos.modelo.Pedido;
import co.edu.poli.BogoThrashManager.RegistroPedidos.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "pedido", description = "Operaciones relacionadas con pedidos")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private NotificacionService notificacionService;

    @Operation(summary = "Crea un pedido", description = "Crea un pedido con todos sus datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "No se pudo crear el pedido")
    })
    @PostMapping
    public ResponseEntity<Pedido> create(
        @Parameter(description = "Nuevo pedido del cliente", required = true)
        @RequestBody PedidoInsertDto pedido) throws Exception {
        Pedido saved = pedidoService.createPedido(pedido);
        if(pedido.getCorreo()!=null) {
        	Notificacion n = new Notificacion();
        	n.setSubject("Pedido realizado con Exito!");
        	n.setToEmail(pedido.getCorreo());
        	n.setBody(saved.toString());
        	notificacionService.sendEmail(n);
        }
        return ResponseEntity.ok(saved);
    }
    
    @PostMapping("/mail")
    public ResponseEntity<String>sendMail(@RequestBody Notificacion n){
    	notificacionService.sendEmail(n);
    	return ResponseEntity.ok("email enviado");
    }

    @Operation(summary = "Consigue todos los pedidos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "No se pudo encontrar los pedidos o no existen")
    })
    @GetMapping
    public ResponseEntity<List<Pedido>> getAll() {
        return ResponseEntity.ok(pedidoService.getAllPedidos());
    }

    @Operation(summary = "Encuentra un pedido por id", description = "A través de una id, busca un pedido en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "No se pudo encontrar el pedido o no existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(
        @Parameter(description = "Id del pedido por buscar", required = true)
        @PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.getPedidoById(id);
        return pedido.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Modifica un pedido por id", description = "Crea una copia del pedido con la id, permitiendo modificarlo más tarde")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "No se pudo encontrar el pedido o no existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(
        @Parameter(description = "Id del pedido a cambiar", required = true)
        @PathVariable Long id,
        @Parameter(description = "Pedido ya modificado", required = true)
        @RequestBody Pedido updatedPedido) {
        Optional<Pedido> updated = pedidoService.updatePedido(id, updatedPedido);
        return updated.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Elimina un pedido", description = "Elimina un pedido de la base de datos utilizando su id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Operación exitosa"),
        @ApiResponse(responseCode = "404", description = "No se pudo encontrar el pedido o no existe")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @Parameter(description = "Id del pedido a eliminar", required = true)
        @PathVariable Long id) {
        boolean deleted = pedidoService.deletePedido(id);
        if (deleted) {
            return ResponseEntity.noContent().build();  // 204 No Content on success
        } else {
            return ResponseEntity.notFound().build();  // 404 if not found
        }
    }
}
