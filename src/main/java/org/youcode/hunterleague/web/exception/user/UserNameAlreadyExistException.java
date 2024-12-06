package org.youcode.hunterleague.web.exception.user;

public class UserNameAlreadyExistException extends RuntimeException{

    public UserNameAlreadyExistException() {
        super("Username already exists");
    }
}
