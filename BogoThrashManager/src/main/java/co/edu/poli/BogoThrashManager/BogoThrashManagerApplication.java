package co.edu.poli.BogoThrashManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//xd
@SpringBootApplication
public class BogoThrashManagerApplication {

	public static void main(String[] args) {
		System.out.println("Servidor Iniciando");
		SpringApplication.run(BogoThrashManagerApplication.class, args);
		System.out.println("Servidor Iniciado Con Exito");
	}

}
