package com.scada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class ScadaboardMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScadaboardMybatisApplication.class, args);
	}

}
