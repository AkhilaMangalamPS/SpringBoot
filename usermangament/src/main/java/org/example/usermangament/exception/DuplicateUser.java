package org.example.usermangament.exception;

public class DuplicateUser extends RuntimeException{
    public DuplicateUser(String message){
        super(message);
    }
}
