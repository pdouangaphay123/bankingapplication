package com.bankingapp.bankingapplication.CustomException;

public class InsufficientFundsException extends RuntimeException{

    public InsufficientFundsException(String message){
        super(message);
    }
}
