package br.com.fiap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class WhereToGoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhereToGoApplication.class, args);
	}

}
