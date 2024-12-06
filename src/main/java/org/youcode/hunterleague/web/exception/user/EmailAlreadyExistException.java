package org.youcode.hunterleague.web.exception.user;

public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException() {
        super("Email already exist");
    }
}
