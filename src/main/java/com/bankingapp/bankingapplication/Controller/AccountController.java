package com.bankingapp.bankingapplication.Controller;

import com.bankingapp.bankingapplication.CustomException.EmailExistsInDbException;
import com.bankingapp.bankingapplication.CustomException.InvalidEmailPasswordException;
import com.bankingapp.bankingapplication.DTO.AmountDepositWithdraw;
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
    public ResponseEntity<?> getBalanceController(Account account){
        try {

            account = accountService.checkBalance(account);
            return ResponseEntity.ok(account);
        } catch (EmailExistsInDbException | InvalidEmailPasswordException e){ // need to work on custom exceptions
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> updateDepositController(@RequestBody AmountDepositWithdraw amount){
        try {

            account = accountService.updateDeposit(amount, account);
            return ResponseEntity.ok(account);
        } catch (EmailExistsInDbException | InvalidEmailPasswordException e){ // need to work on custom exceptions
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> updateWithdrawController(@RequestBody AmountDepositWithdraw amount){
        try {

            account = accountService.updateWithdraw(amount, account);
            return ResponseEntity.ok(account);
        } catch (EmailExistsInDbException | InvalidEmailPasswordException e){ // need to work on custom exceptions
            return ResponseEntity.badRequest().body(e);
        }
    }
}
