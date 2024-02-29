package com.bankingapp.bankingapplication.Service;

import com.bankingapp.bankingapplication.Controller.AuthController;
import com.bankingapp.bankingapplication.Model.Account;
import com.bankingapp.bankingapplication.Model.User;
import com.bankingapp.bankingapplication.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    AuthController authController;

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;

    }


    public Account getBalance(User user){

        Account account = new Account();
        // if found if not found then throw exception
        account = accountRepository.findByUserId(authController.getSessionUserId());
        return account;
    }

}
