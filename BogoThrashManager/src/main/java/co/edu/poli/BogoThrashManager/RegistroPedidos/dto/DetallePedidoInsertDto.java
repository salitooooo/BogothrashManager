package co.edu.poli.BogoThrashManager.RegistroPedidos.dto;

import java.util.List;

import co.edu.poli.BogoThrashManager.RegistroInventario.modelo.Producto;
import lombok.Data;

@Data
public class DetallePedidoInsertDto {

	private List<Producto> productos;
	
	private Long precioTotal;
	
}
