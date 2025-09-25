package co.edu.poli.BogoThrashManager.repository;
import co.edu.poli.BogoThrashManager.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // No need to implement methods—JpaRepository provides CRUD (save, findAll, etc.)
}
