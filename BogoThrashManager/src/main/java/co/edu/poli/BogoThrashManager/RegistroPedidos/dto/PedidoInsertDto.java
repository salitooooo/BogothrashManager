package co.edu.poli.BogoThrashManager.RegistroPedidos.dto;

import co.edu.poli.BogoThrashManager.RegistroPedidos.modelo.DetallePedido;
import lombok.Data;

@Data
public class PedidoInsertDto {

	private DetallePedidoInsertDto detalle;
	
	private String cliente;
	
	private String correo;
	
	private String barista;
	
	private String formaDePago;
	
}
