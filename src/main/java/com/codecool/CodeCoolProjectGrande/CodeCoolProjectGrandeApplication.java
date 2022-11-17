package com.codecool.CodeCoolProjectGrande;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAdminServer
@SpringBootApplication
public class CodeCoolProjectGrandeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeCoolProjectGrandeApplication.class, args);
	}

}
