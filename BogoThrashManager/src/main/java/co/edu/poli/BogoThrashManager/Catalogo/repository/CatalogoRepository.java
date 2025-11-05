package co.edu.poli.BogoThrashManager.Catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.BogoThrashManager.Catalogo.modelo.Articulo;

@Repository
public interface CatalogoRepository extends JpaRepository<Articulo, Long> {

}
