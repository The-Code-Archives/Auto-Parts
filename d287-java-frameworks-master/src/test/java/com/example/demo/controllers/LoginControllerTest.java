package com.example.demo.controllers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public class LoginControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        LoginController controller = new LoginController();
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".html");

        mockMvc = standaloneSetup(controller)
                .setViewResolvers(viewResolver)
                .build();
    }
//Test 1
    @Test
    public void testValidLogin() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "Carter")
                        .param("password", "Password907"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/mainscreen"))
                .andExpect(flash().attribute("username", "Carter"));
    }
//Test 2
    @Test
    public void testInvalidLogin() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "InvalidUser")
                        .param("password", "InvalidPassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }
}
