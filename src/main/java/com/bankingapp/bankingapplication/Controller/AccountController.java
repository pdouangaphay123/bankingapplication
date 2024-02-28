package com.bankingapp.bankingapplication.Controller;

import com.bankingapp.bankingapplication.CustomException.EmailExistsInDbException;
import com.bankingapp.bankingapplication.CustomException.InvalidEmailPasswordException;
import com.bankingapp.bankingapplication.DTO.AmountDepositWithdraw;
import com.bankingapp.bankingapplication.Model.Account;
import com.bankingapp.bankingapplication.Model.User;
import com.bankingapp.bankingapplication.Service.AccountService;
import com.bankingapp.bankingapplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@CrossOrigin (origins = {"https://localhost:8080"},
        allowedHeaders = "*", exposedHeaders = "*", allowCredentials = "true", maxAge = 244444)
public class AccountController {

    AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService){ this.accountService = accountService;}

    @PostMapping("/register")
    public ResponseEntity<?> getBalanceHandler(@RequestBody User user){
        try {

             Account account = accountService.getBalance(user);
            return ResponseEntity.ok(account.getBalance());
        } catch (EmailExistsInDbException | InvalidEmailPasswordException e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
