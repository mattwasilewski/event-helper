package com.codecool.CodeCoolProjectGrande.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

//    @GetMapping("/userlogin")
//    public String loginPage(Model model){
//        User testUser = new User();
//        testUser.setEmail("bartek@gmail.com");
//        System.out.println(testUser.getEmail());
//        model.addAttribute("users", testUser);
//        return "login";
//    }

        @GetMapping("/login")
        public String login(){
            return "index";
        }

        @GetMapping("/homepage")
        public String homepage(){
            return "homepage";
        }

}
