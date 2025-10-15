package co.edu.poli.BogoThrashManager.RegistroInventario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.Producto;
import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.ProductoBebida;
import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.ProductoSnack;
import co.edu.poli.BogoThrashManager.RegistroInventario.repository.InventarioRepository;

@Service
public class ProductoService {
	@Autowired 
	private InventarioRepository productoRepository;
	public Producto findOrCreateProducto (Producto producto) {
		//verificar polimorfismo
	if (producto instanceof ProductoSnack) {
		ProductoSnack snack = (ProductoSnack) producto;
		return productoRepository.findByNombreAndTipoNombreAndEsDulceAndEsVegano(snack.getNombre(),
				snack.getTipo().getNombre(),snack.isEsDulce(),snack.isEsVegano()).orElseGet(() -> {
                    // Create and save new if not found
                    ProductoSnack newSnack = new ProductoSnack();
                    newSnack.setNombre(snack.getNombre());
                    newSnack.setPrecio(snack.getPrecio());
                    newSnack.setTipo(snack.getTipo());  // Assume tipo is managed separately or fetched
                    newSnack.setEsDulce(snack.getEsDulce());
                    newSnack.setEsVegano(snack.getEsVegano());
                    return productoRepository.save(newSnack);
                });
	}
		else if (producto instanceof ProductoBebida) {
            ProductoBebida bebida = (ProductoBebida) producto;
            return productoRepository.findByNombreAndTipoNombreAndEsCalienteAndTieneAlcohol(
                bebida.getNombre(), 
                bebida.getTipo().getNombre(), 
                bebida.isEsCaliente(), 
                bebida.isTieneAlcohol()
            ).orElseGet(() -> {
                // Similar creation logic for Bebida
                ProductoBebida newBebida = new ProductoBebida();
                newBebida.setNombre(bebida.getNombre());
                newBebida.setPrecio(bebida.getPrecio());
                newBebida.setTipo(bebida.getTipo());
                newBebida.setEsCaliente(bebida.getEsCaliente());
                newBebida.setTieneAlcohol(bebida.getTieneAlcohol());
                return productoRepository.save(newBebida);
            });
        }
	}
	}
}
