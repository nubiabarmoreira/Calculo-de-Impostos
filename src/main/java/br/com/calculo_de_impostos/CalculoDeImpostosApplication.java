package br.com.calculo_de_impostos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.calculo_de_impostos")
public class CalculoDeImpostosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculoDeImpostosApplication.class, args);
	}
}