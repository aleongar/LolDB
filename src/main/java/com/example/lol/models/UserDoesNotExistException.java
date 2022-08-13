package com.example.lol.models;

public class UserDoesNotExistException extends Throwable {
    public UserDoesNotExistException(){
        super("The user data does not exist on the Database");
    }
}