package co.edu.poli.BogoThrashManager.RegistroPedidos.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.BogoThrashManager.RegistroPedidos.modelo.Pedido;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // No need to implement methods—JpaRepository provides CRUD (save, findAll, etc.)
}
