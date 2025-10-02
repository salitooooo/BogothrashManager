package co.edu.poli.BogoThrashManager.Notificaciones.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.BogoThrashManager.Notificaciones.service.NotificacionService;
import co.edu.poli.BogoThrashManager.RegistroPedidos.modelo.Pedido;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/notificaciones")

public class NotificacionController {
	
	@Autowired
	private NotificacionService notificacionService;
	
	@PostMapping
	public ResponseEntity <String> create (){
	return ResponseEntity.ok("notificacion exitosa");
	
	}
	 @GetMapping
	 public ResponseEntity <String> getAll() {
	 return ResponseEntity.ok("notificacion");
	    }
	 @GetMapping("/{id}")
	 public ResponseEntity<String> getById(){
	 return ResponseEntity.ok("notificacion");
	                     
	 }
	 @PutMapping("/{id}")
	 public ResponseEntity <String> update(){
	return ResponseEntity.ok("notificacion actualizada");
		 
	 }
	
	 @DeleteMapping("/{id}")
	 public ResponseEntity <String> delete(){
	return ResponseEntity.ok("notificacion eliminada");
	 }
	

}
