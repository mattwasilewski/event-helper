package com.codecool.CodeCoolProjectGrande;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableAdminServer
@SpringBootApplication
public class CodeCoolProjectGrandeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeCoolProjectGrandeApplication.class, args);
	}

}
