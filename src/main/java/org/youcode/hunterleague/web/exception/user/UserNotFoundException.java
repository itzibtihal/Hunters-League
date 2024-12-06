package org.youcode.hunterleague.web.exception.user;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("user does not exist");
    }
}
