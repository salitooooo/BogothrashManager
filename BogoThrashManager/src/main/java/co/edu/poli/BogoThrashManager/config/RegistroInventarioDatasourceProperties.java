package co.edu.poli.BogoThrashManager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
	    basePackages = "co.edu.poli.PolisongStock.RegistroCancion.repository",
	    entityManagerFactoryRef = "cancionEntityManagerFactory",
	    transactionManagerRef = "cancionTransactionManager"
	)
public class RegistroInventarioDatasourceProperties {

}
