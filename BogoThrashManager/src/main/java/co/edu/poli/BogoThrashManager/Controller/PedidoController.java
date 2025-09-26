package co.edu.poli.BogoThrashManager.Controller;
import co.edu.poli.BogoThrashManager.modelo.Pedido;
import co.edu.poli.BogoThrashManager.service.PedidoService;
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
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
@Autowired
private PedidoService pedidoService;

@PostMapping
public ResponseEntity create(@RequestBody Pedido pedido) {
Pedido saved = pedidoService.createPedido(pedido);
return ResponseEntity.ok(saved);
}

@GetMapping
public ResponseEntity<List> getAll() {
return ResponseEntity.ok(pedidoService.getAllPedidos());
}

@GetMapping("/{id}")
public ResponseEntity<Pedido> getById(@PathVariable Long id) {
    Optional<Pedido> pedido = pedidoService.getPedidoById(id);
    return pedido.map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
}

@PutMapping("/{id}")
public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody Pedido updatedPedido) {
    Optional<Pedido> updated = pedidoService.updatePedido(id, updatedPedido);
    return updated.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());  // 404 if not found
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id) {
    boolean deleted = pedidoService.deletePedido(id);
    if (deleted) {
        return ResponseEntity.noContent().build();  // 204 No Content on success
    } else {
        return ResponseEntity.notFound().build();  // 404 if not found
    }
}
}
