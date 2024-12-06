package org.youcode.hunterleague.web.exception.competition;

public class CompetitionAlreadyExistException extends RuntimeException {
    public CompetitionAlreadyExistException(String message) {
        super(message);
    }
}
