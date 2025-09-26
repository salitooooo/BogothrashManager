package co.edu.poli.BogoThrashManager.service;
import co.edu.poli.BogoThrashManager.modelo.Pedido;
import co.edu.poli.BogoThrashManager.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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
}
