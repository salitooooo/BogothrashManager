package co.edu.poli.BogoThrashManager.Controller;
import co.edu.poli.BogoThrashManager.modelo.Pedido;
import co.edu.poli.BogoThrashManager.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
@Autowired
private PedidoService pedidoService;
@PostMapping
public ResponseEntity create(@RequestBody Pedido pedido) {
Pedido saved = pedidoService.createPedido(pedido.getDetalle());
return ResponseEntity.ok(saved);
}
@GetMapping
public ResponseEntity<List> getAll() {
return ResponseEntity.ok(pedidoService.getAllPedidos());
}
}
