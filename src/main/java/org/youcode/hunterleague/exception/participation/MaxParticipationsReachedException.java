package org.youcode.hunterleague.exception.participation;

public class MaxParticipationsReachedException extends RuntimeException {
    public MaxParticipationsReachedException(String message) {
        super(message);
    }
}
