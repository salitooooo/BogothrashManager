package co.edu.poli.BogoThrashManager.RegistroPedidos.service;
import co.edu.poli.BogoThrashManager.RegistroPedidos.modelo.Pedido;
import co.edu.poli.BogoThrashManager.RegistroPedidos.repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class PedidoService {
@Autowired
private PedidoRepository pedidoRepository;

public Pedido createPedido(Pedido pedido) {
return pedidoRepository.save(pedido); // This triggers JPA to insert into DB
}

public List getAllPedidos() {
return pedidoRepository.findAll();
}

public Optional<Pedido> updatePedido(Long id, Pedido updatedPedido) {
    return pedidoRepository.findById(id).map(pedido -> {
        // Update fields if provided (non-null check optional for simplicity)
        pedido.setDetalle(updatedPedido.getDetalle() != null ? updatedPedido.getDetalle() : pedido.getDetalle());
        pedido.setCliente(updatedPedido.getCliente() != null ? updatedPedido.getCliente() : pedido.getCliente());
        pedido.setBarista(updatedPedido.getBarista() != null ? updatedPedido.getBarista() : pedido.getBarista());
        return pedidoRepository.save(pedido);  // Saves the updated entity
    });
}

public boolean deletePedido(Long id) {
    if (pedidoRepository.existsById(id)) {
        pedidoRepository.deleteById(id);
        return true;
    }
    return false;  // Returns false if ID not found
}

public Optional<Pedido> getPedidoById(Long id) {
    return pedidoRepository.findById(id);
}

}
