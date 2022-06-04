package br.com.franca.springbootdeveficiente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(enableDefaultTransactions = false)
@SpringBootApplication
public class SpringBootDevEficienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDevEficienteApplication.class, args);
	}

}
