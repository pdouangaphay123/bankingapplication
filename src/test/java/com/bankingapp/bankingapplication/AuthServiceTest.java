package com.bankingapp.bankingapplication;

import com.bankingapp.bankingapplication.DTO.LoginCreds;
import com.bankingapp.bankingapplication.Model.Account;
import com.bankingapp.bankingapplication.Model.User;
import com.bankingapp.bankingapplication.Repository.AccountRepository;
import com.bankingapp.bankingapplication.Repository.AuthRepository;
import com.bankingapp.bankingapplication.Service.AuthService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthServiceTest {

    // testing AuthService methods
    private MockMvc mockMvc;

    @Mock
    private AuthRepository authRepository;
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AuthService authService;

    // mock user
    User user1 = new User(1, "janedoe@gmail.com", "password1", "Jane", null);
    User user2 = new User(4, "jake007@gmail.com", "password1", "Jake", null);

    @Before
    public void setUp(){ // setting up mock build for the class
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authService).build();
    }

    @Test
    public void loginUser_success() throws Exception{ // mock it like it has a database

        User loggedinUser = new User();
        loggedinUser.setUserId(4);
        loggedinUser.setEmail("janedoe@gmail.com");
        loggedinUser.setPassword("password1");
        loggedinUser.setCustomerName("Jane");
        loggedinUser.setAccount(null);

        LoginCreds loginCreds = new LoginCreds();
        loginCreds.setEmail("janedoe@gmail.com");
        loginCreds.setPassword("password1"); // make a dto object

        Mockito.when(authRepository.findByEmail(loginCreds.getEmail())).thenReturn(loggedinUser);

        User user = authService.loginUser(loginCreds);

        assertNotNull(user);
        assertEquals("janedoe@gmail.com", user.getEmail());
    }

    @Test
    public void createUser_success() throws Exception{ // mock it like it has a database
        
        // assuming this is a new user  that passed the validations
        User mockUser = new User();
        mockUser.setUserId(4);
        mockUser.setEmail("jake007@gmail.com");
        mockUser.setPassword("password1");
        mockUser.setCustomerName("Jake");
        mockUser.setAccount(null);

        Account account = new Account();
        Mockito.when(authRepository.save(user2)).thenReturn(mockUser); // mocking return type after method call
        if (authRepository.existsById(mockUser.getUserId())) {
            account.setUser(mockUser);
            account.getUser().setUserId(mockUser.getUserId());
            account = accountRepository.save(account);
        }

        User user = authService.createUser(mockUser);

        assertNotNull(user);
        assertEquals(mockUser.getUserId(), user.getUserId(), user2.getUserId());
        assertNotNull(account);
    }
}


