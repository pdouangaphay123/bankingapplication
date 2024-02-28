package com.bankingapp.bankingapplication.CustomException;

public class EmailExistsInDbException extends RuntimeException{

    public EmailExistsInDbException(String message){
        super(message);
    }
}
