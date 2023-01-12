package com.codecool.CodeCoolProjectGrande.user.controller;

import com.codecool.CodeCoolProjectGrande.user.User;
import com.codecool.CodeCoolProjectGrande.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@WebMvcTest(RegisterController.class)
@AutoConfigureMockMvc(addFilters = false)
public class RegisterControllerTest {


    @MockBean
    UserService userService;
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldCreateMockMvc(){
        assertNotNull(mockMvc);
    }

    @Test
     public void newUserCreateShouldReturn201WhileUserDataIsCorrect() throws Exception {
        when(userService.isUserDataValid(Mockito.any(User.class))).thenReturn(true);
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Maciek\",\"email\":\"maciek22@gmail.com\",\"password\":\"Maciek231\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void newUserWithIncorrectDataShouldReturn400() throws Exception {
        when(userService.isUserDataValid(Mockito.any(User.class))).thenReturn(false);
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Maciek\",\"email\":\"maciek22\",\"password\":\"Maciek231\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}