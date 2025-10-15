package co.edu.poli.BogoThrashManager.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
	    basePackages = "co.edu.poli.BogoThrashManager.RegistroInventario.repository",
	    entityManagerFactoryRef = "productoEntityManagerFactory",
	    transactionManagerRef = "productoTransactionManager"
	)
public class RegistroInventarioDatasourceProperties {
	

    @Bean(name = "productoDataSource")
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:6543/postgres?sslmode=require");
        System.out.println("sapopinga!");
        config.setUsername("postgres.fvgdqthrljrtmexqhfxa");
        config.setPassword("Servidor123");
        config.setDriverClassName("org.postgresql.Driver");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setConnectionTestQuery("SELECT 1");
        config.setConnectionTimeout(30000);
        config.setValidationTimeout(5000);

        // Evitar prepared statements del lado servidor
        config.addDataSourceProperty("prepareThreshold", "0");
        config.addDataSourceProperty("preferQueryMode", "simple");

        // Hibernate necesita autoCommit = false para gestionar transacciones
        config.setAutoCommit(false);

        DataSource ds = new HikariDataSource(config);
        System.out.println("HARDCODED DataSource created with URL: " + config.getJdbcUrl());
        return ds;
    }

    @Bean(name = "productoEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("productoDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = builder
                .dataSource(dataSource)
                .packages("co.edu.poli.BogoThrashManager.RegistroInventario.modelo")
                .persistenceUnit("producto")
                .build();
        em.setJpaProperties(hibernateProperties());
        return em;
    }

    @Bean(name = "productoTransactionManager")
    public PlatformTransactionManager cancionTransactionManager(
            @Qualifier("productoEntityManagerFactory") LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(emf.getObject());
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        return properties;
    }

}
