package co.edu.poli.BogoThrashManager.RegistroInventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.Producto;



public interface InventarioRepository extends JpaRepository<Producto, Long> {

}
