package co.edu.poli.BogoThrashManager.Catalogo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.poli.BogoThrashManager.Catalogo.modelo.Articulo;

@Repository
public interface CatalogoRepository extends JpaRepository<Articulo, Long> {
	@Query("SELECT c FROM  catalogo WHERE c.nombre =:nombre")
	Optional<Articulo>findByNombre(
			@Param("nombre") String nombre
	);
	
	

}
