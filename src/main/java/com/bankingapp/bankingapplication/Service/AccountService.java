package com.bankingapp.bankingapplication.Service;

import com.bankingapp.bankingapplication.Controller.AuthController;
import com.bankingapp.bankingapplication.Model.Account;
import com.bankingapp.bankingapplication.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;


@Service
public class AccountService {

    private AuthController authController;

    private final AccountRepository accountRepository;

    private Account account;

    @Autowired
    public AccountService(AccountRepository accountRepository, AuthController authController) {
        this.accountRepository = accountRepository;
        this.authController = authController;
    }


    public Account checkBalance(Account account){

        ExampleMatcher userIdMatcher = ExampleMatcher.matching() // checking userId existence in repo
                .withIgnorePaths("accountId")
                .withMatcher("userId", ignoreCase());
        account.setUserId(authController.getSessionUserId());
        Example<Account> example = Example.of(account, userIdMatcher);

        if(accountRepository.exists(example)) {
            account.setUserId(authController.getSessionUserId());
            account = accountRepository.getByUserId(authController.getSessionUserId());

            return account;
        }
        else {
            return null; // need a custom exception in place
        }
    }
}
