package co.edu.poli.BogoThrashManager.RegistroPedidos.service;
import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.Producto;
import co.edu.poli.BogoThrashManager.RegistroInventario.service.ProductoService;
import co.edu.poli.BogoThrashManager.RegistroPedidos.modelo.Pedido;
import co.edu.poli.BogoThrashManager.RegistroPedidos.repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
@Autowired
private PedidoRepository pedidoRepository;
@Autowired
private ProductoService productoService;

public Pedido createPedido(Pedido pedido) {
    if (pedido.getDetalle() != null && pedido.getDetalle().getProductos() != null) {
        List<Producto> uniqueProductos = new ArrayList<>();
        for (Producto incomingProducto : pedido.getDetalle().getProductos()) {
            Producto existingOrNew = productoService.findOrCreateProducto(incomingProducto);
            uniqueProductos.add(existingOrNew);
        }
        pedido.getDetalle().setProductos(uniqueProductos);  // Replace with unique instances
    }
    
    // Now save - only associations will be created in join table
    return pedidoRepository.save(pedido);
}

public List getAllPedidos() {
return pedidoRepository.findAll();
}

public Optional<Pedido> updatePedido(Long id, Pedido updatedPedido) {
    return pedidoRepository.findById(id).map(pedido -> {
        if (updatedPedido.getDetalle() != null) {
            if (pedido.getDetalle() == null) {
                pedido.setDetalle(updatedPedido.getDetalle());
            } else {
                // Update nested fields inside detalle
                pedido.getDetalle().setPrecioTotal(
                    updatedPedido.getDetalle().getPrecioTotal() != null ? 
                    updatedPedido.getDetalle().getPrecioTotal() : 
                    pedido.getDetalle().getPrecioTotal()
                );

                if (updatedPedido.getDetalle().getProductos() != null) {
                	List<Long> existingProductos = pedido.getDetalle().getProductos();
                	List<Long> updatedProductos = updatedPedido.getDetalle().getProductos();
                	existingProductos.clear();  // Remove all old elements (or selectively remove)
                	existingProductos.addAll(updatedProductos);  // Add new elements
                }
            }
        }

        pedido.setCliente(updatedPedido.getCliente() != null ? updatedPedido.getCliente() : pedido.getCliente());
        pedido.setBarista(updatedPedido.getBarista() != null ? updatedPedido.getBarista() : pedido.getBarista());
        pedido.setFechaCompra(updatedPedido.getFechaCompra() != null ? updatedPedido.getFechaCompra() : pedido.getFechaCompra());
        pedido.setCupon(updatedPedido.isCupon());
        pedido.setFormaDePago(updatedPedido.getFormaDePago() != null ? updatedPedido.getFormaDePago() : pedido.getFormaDePago());

        return pedidoRepository.save(pedido);
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
