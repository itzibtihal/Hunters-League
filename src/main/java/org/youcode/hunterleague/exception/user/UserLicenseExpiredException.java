package org.youcode.hunterleague.exception.user;

import java.time.LocalDate;

public class UserLicenseExpiredException extends RuntimeException {
    public UserLicenseExpiredException(LocalDate expirationDate) {
        super("User license expired on " + expirationDate);
    }
}
