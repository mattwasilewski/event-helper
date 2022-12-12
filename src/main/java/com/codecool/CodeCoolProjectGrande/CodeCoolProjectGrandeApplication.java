package com.codecool.CodeCoolProjectGrande;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@EnableAdminServer
@SpringBootApplication
public class CodeCoolProjectGrandeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeCoolProjectGrandeApplication.class, args);
	}

}
