package co.edu.poli.BogoThrashManager.Notificaciones.modelo;

import java.util.*;

import co.edu.poli.BogoThrashManager.RegistroPedidos.modelo.DetallePedido;
import co.edu.poli.BogoThrashManager.RegistroPedidos.modelo.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {
	
	private String toEmail;
	
	private String subject;
	
	private String body;
}