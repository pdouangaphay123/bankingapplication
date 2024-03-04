package com.bankingapp.bankingapplication;

import com.bankingapp.bankingapplication.Controller.AuthController;
import com.bankingapp.bankingapplication.Model.User;
import com.bankingapp.bankingapplication.Service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    // API endpoint tests
    private MockMvc mockMvc; //  Spring Boot test tool class that lets you test controllers without needing to start an HTTP server

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    // mock user
    User user1 = new User(1, "janedoe@gmail.com", "password1", "Jane", null);
    User user2 = new User(2, "phonexay@gmail.com", "password1", "Phonexay", null);

    @Before
    public void setUp(){ // setting up mock build for the class
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void registerUser_success() throws Exception{

        Mockito.when(authService.createUser(user1)).thenReturn(user1); // mocking return type after method call

        String content = objectWriter.writeValueAsString(user1); // writing user as a json content

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("janedoe@gmail.com"));
    }
}
