package com.labdev.labdev_spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.labdev.labdev_spring.models.Agente;
import com.labdev.labdev_spring.repositories.AgenteRepository;

@SpringBootApplication
public class LabdevSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabdevSpringApplication.class, args);
	}

	@Bean
	public CommandLineRunner seedAgente(AgenteRepository agenteRepo, PasswordEncoder encoder) {
		return args -> {
			if (agenteRepo.findByEmail("agente@empresa.com").isEmpty()) {
				Agente agente = new Agente();
				agente.setNome("Agente Teste");
				agente.setEmail("agente@empresa.com");
				agente.setSenha(encoder.encode("123456"));
				agente.setRole("ROLE_AGENTE");
				agenteRepo.save(agente);
				System.out.println("Agente criado: agente@empresa.com / 123456");
			}
		};
	}

}
