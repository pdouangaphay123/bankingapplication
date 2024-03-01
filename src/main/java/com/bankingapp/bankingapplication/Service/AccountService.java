package com.bankingapp.bankingapplication.Service;

import com.bankingapp.bankingapplication.Controller.AuthController;
import com.bankingapp.bankingapplication.CustomException.InsufficientFundsException;
import com.bankingapp.bankingapplication.DTO.AmountDepositWithdraw;
import com.bankingapp.bankingapplication.DTO.AmountTransferTransaction;
import com.bankingapp.bankingapplication.Model.Account;
import com.bankingapp.bankingapplication.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;


@Service
public class AccountService {

    private final AuthController authController;

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, AuthController authController) {
        this.accountRepository = accountRepository;
        this.authController = authController;
    }

    public Account checkBalance(Account account) {

            if(authController.getSessionUserId() != null ) {
                account = accountRepository.getByUserUserId(authController.getSessionUserId());
                return account;
            }
            else throw new RuntimeException("Need to be log on to use this service");
    }

    public Account updateDeposit(AmountDepositWithdraw amountDepositWithdraw, Account account) {

        account = accountRepository.getByUserUserId(authController.getSessionUserId());
        account.setBalance(account.getBalance() + amountDepositWithdraw.getAmount());

        return accountRepository.save(account);
    }

    public Account updateWithdraw(AmountDepositWithdraw amountDepositWithdraw, Account account) {

        account = accountRepository.getByUserUserId(authController.getSessionUserId());
        // conditional for insufficient funds
        if(account.getBalance() > amountDepositWithdraw.getAmount()) {
            account.setBalance(account.getBalance() - amountDepositWithdraw.getAmount());

            return accountRepository.save(account);
        } else throw new InsufficientFundsException("Insufficient funds");
    }


    public Account updateTransfer(AmountTransferTransaction amountTransferTransaction, Account sendingAccount) {

        sendingAccount = accountRepository.getByUserUserId(authController.getSessionUserId());
        if(sendingAccount.getBalance() > amountTransferTransaction.getAmount()) {
            // sending account withdraw
            sendingAccount.setBalance(sendingAccount.getBalance() - amountTransferTransaction.getAmount());
            // receiving account deposit
            Account receivingAccount = accountRepository.getByUserUserId(amountTransferTransaction.getTransferId());
            receivingAccount.setBalance(receivingAccount.getBalance() + amountTransferTransaction.getAmount());

            return accountRepository.save(sendingAccount);
        } else throw new InsufficientFundsException("Insufficient funds");
    }
}
