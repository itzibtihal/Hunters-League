package org.youcode.hunterleague.web.exception.species;

public class SpeciesNotFoundException extends RuntimeException{
    public SpeciesNotFoundException(String message) {
        super(message);
    }
}
