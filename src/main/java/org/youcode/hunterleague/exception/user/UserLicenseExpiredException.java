package org.youcode.hunterleague.exception.user;

import java.time.LocalDateTime;

public class UserLicenseExpiredException extends RuntimeException {
    public UserLicenseExpiredException(LocalDateTime expirationDate) {
        super("User license expired on " + expirationDate);
    }
}
