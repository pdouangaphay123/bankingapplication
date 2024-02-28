package com.bankingapp.bankingapplication.CustomException;

public class InvalidEmailPasswordException extends RuntimeException{

    public InvalidEmailPasswordException(String message){
        super(message);
    }
}
