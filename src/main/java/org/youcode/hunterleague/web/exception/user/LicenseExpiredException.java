package org.youcode.hunterleague.web.exception.user;

public class LicenseExpiredException extends RuntimeException {
    public LicenseExpiredException(String message) {
        super(message);
    }
}
