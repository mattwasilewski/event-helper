package com.codecool.CodeCoolProjectGrande;

import com.codecool.CodeCoolProjectGrande.account.user.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodeCoolProjectGrandeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeCoolProjectGrandeApplication.class, args);
		UserController userController = new UserController();
		userController.getUsers().getAllUsers().forEach(user -> System.out.println(user.getName()));
	}

}
