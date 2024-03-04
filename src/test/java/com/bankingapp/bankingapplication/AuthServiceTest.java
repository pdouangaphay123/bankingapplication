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

        LoginCreds loginCreds = new LoginCreds();
        loginCreds.setEmail("janedoe@gmail.com");
        loginCreds.setPassword("password1"); // make a dto object

        Mockito.when(authRepository.findByEmail(loginCreds.getEmail())).thenReturn(user1);

        User loggedUser = authService.loginUser(loginCreds);

        assertNotNull(loggedUser);
        assertEquals("janedoe@gmail.com", loggedUser.getEmail());
        assertEquals("password1", loggedUser.getPassword());
    }

    @Test
    public void createUser_success() throws Exception{ // mock it like it has a database
        
        // assuming this is a new user  that passed the validations
        User mockUser = new User();
        mockUser.setUserId(2);
        mockUser.setEmail("phonexay@gmail.com");
        mockUser.setPassword("password1");
        mockUser.setCustomerName("Phonexay");
        mockUser.setAccount(null);

        Account account = new Account();
        Mockito.when(authRepository.save(mockUser)).thenReturn(mockUser); // mocking return type after method call

        User registeredUser = authService.createUser(mockUser);

        assertNotNull(registeredUser);
        assertEquals("Phonexay", mockUser.getCustomerName(), registeredUser.getCustomerName());
        // account should be instantiated and not null with balance defaulted at 0.00
        // and account id at 0 since there is no database in this test, no auto increment
        assertNotNull(account);
        assertEquals(0.00, account.getBalance());
        assertEquals(0, account.getAccountId());
    }
}


