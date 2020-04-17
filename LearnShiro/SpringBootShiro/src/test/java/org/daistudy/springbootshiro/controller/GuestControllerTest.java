package org.daistudy.springbootshiro.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private MockHttpSession session;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); // 初始化 MockMvc
        session = new MockHttpSession();
    }

    @Test
    void login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/guest/enter").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
//              .andExpect(MockMvcResultMatchers.jsonPath("result").value("success"))
//              .andExpect(MockMvcResultMatchers.jsonPath("message").value("欢迎进入，您的身份是游客"))
                .andDo(MockMvcResultHandlers.print())
        .andReturn();
    }

    @Test
    void submitLogin() {
    }
}