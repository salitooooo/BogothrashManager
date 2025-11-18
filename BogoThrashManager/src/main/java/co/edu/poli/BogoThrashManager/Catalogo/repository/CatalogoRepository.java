package co.edu.poli.BogoThrashManager.Catalogo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.poli.BogoThrashManager.Catalogo.modelo.Articulo;

@Repository
public interface CatalogoRepository extends JpaRepository<Articulo, Long> {

	Optional<Articulo>findByNombre(
			String nombre
	);

	List<Articulo>findAllByCategoria(
			String categoria
	);
	

}
