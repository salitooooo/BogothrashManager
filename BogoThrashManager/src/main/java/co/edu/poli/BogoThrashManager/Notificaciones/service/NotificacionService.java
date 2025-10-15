package co.edu.poli.BogoThrashManager.Notificaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import co.edu.poli.BogoThrashManager.Notificaciones.modelo.Notificacion;

@Service
public class NotificacionService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(Notificacion n) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("bogothrashnotifications@gmail.com");
		message.setTo(n.getToEmail());
		message.setSubject(n.getSubject());
		message.setText(n.getBody());
		javaMailSender.send(message);
	}

}
