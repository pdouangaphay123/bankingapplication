package com.bankingapp.bankingapplication.Service;

import com.bankingapp.bankingapplication.Controller.AuthController;
import com.bankingapp.bankingapplication.DTO.AmountDepositWithdraw;
import com.bankingapp.bankingapplication.Model.Account;
import com.bankingapp.bankingapplication.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

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


    public Account checkBalance(Account account) {

            account = accountRepository.getByUserId(authController.getSessionUserId());

            return account;

    }

    public Account updateDeposit(AmountDepositWithdraw amount, Account account) {

        account = accountRepository.getByUserId(authController.getSessionUserId());

        account.setBalance(account.getBalance() - amount.getAmount());

        return accountRepository.save(account);
    }

    public Account updateWithdraw(AmountDepositWithdraw amount, Account account) {

        account = accountRepository.getByUserId(authController.getSessionUserId());
        // conditional for insufficient funds
        //if(account.getBalance() > amount)

        account.setBalance(account.getBalance() - amount.getAmount());
        return accountRepository.save(account);
    }


    
}
