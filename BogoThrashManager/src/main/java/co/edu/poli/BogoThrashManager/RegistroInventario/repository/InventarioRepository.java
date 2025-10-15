package co.edu.poli.BogoThrashManager.RegistroInventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.Producto;


@Repository
public interface InventarioRepository extends JpaRepository<Producto, Long> {
	@Query("SELECT p FROM Producto p WHERE p.nombre = :nombre AND p.tipo.nombre = :tipoNombre AND p.EsDulce = :EsDulce AND p.EsVegano = :EsVegano")
	public Producto findByNombreAndTipoNombreAndEsDulceAndEsVegano();
	
	@Query("SELECT p FROM Producto p WHERE p.nombre = :nombre AND p.tipo.nombre = :tipoNombre AND p.EsCaliente = :EsCaliente AND p.TieneAlcohol = :TieneAlcohol")
	public Producto findByNombreAndTipoNombreAndEsCalienteAndTieneAlcohol();

}
