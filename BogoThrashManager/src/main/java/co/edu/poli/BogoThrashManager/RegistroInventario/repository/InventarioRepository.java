package co.edu.poli.BogoThrashManager.RegistroInventario.repository;

import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.Producto;
import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.ProductoBebida;
import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.ProductoSnack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Producto, Long> {
    // Existing methods...

    @Query("SELECT p FROM ProductoBebida p WHERE p.nombre = :nombre AND p.tipo.nombre = :tipoNombre AND p.esCaliente = :esCaliente AND p.tieneAlcohol = :tieneAlcohol")
    Optional<ProductoBebida> findByNombreAndTipoNombreAndEsCalienteAndTieneAlcohol(
        @Param("nombre") String nombre,
        @Param("tipoNombre") String tipoNombre,
        @Param("esCaliente") boolean esCaliente,
        @Param("tieneAlcohol") boolean tieneAlcohol
    );

    // Similarly, for ProductoSnack if needed
    @Query("SELECT p FROM ProductoSnack p WHERE p.nombre = :nombre AND p.tipo.nombre = :tipoNombre AND p.esDulce = :esDulce AND p.esVegano = :esVegano")
    Optional<ProductoSnack> findByNombreAndTipoNombreAndEsDulceAndEsVegano(
        @Param("nombre") String nombre,
        @Param("tipoNombre") String tipoNombre,
        @Param("esDulce") boolean esDulce,
        @Param("esVegano") boolean esVegano
    );
}