package com.bankingapp.bankingapplication.Controller;

import com.bankingapp.bankingapplication.CustomException.EmailExistsInDbException;
import com.bankingapp.bankingapplication.CustomException.InvalidEmailPasswordException;
import com.bankingapp.bankingapplication.Model.Account;
import com.bankingapp.bankingapplication.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/account")
@CrossOrigin (origins = {"https://localhost:8080"},
        allowedHeaders = "*", exposedHeaders = "*", allowCredentials = "true", maxAge = 244444)
public class AccountController {

    private final AccountService accountService;
    private Account account;
    @Autowired
    public AccountController(AccountService accountService){ this.accountService = accountService;}

    @GetMapping("/balance")
    public ResponseEntity<?> getBalanceHandler(Account account){
        try {

            account = accountService.checkBalance(account);
            return ResponseEntity.ok(account);
        } catch (EmailExistsInDbException | InvalidEmailPasswordException e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
