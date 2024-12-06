package org.youcode.hunterleague.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.youcode.hunterleague.web.exception.competition.CompetitionAlreadyExistException;
import org.youcode.hunterleague.web.exception.competition.CompetitionNotFoundException;
import org.youcode.hunterleague.web.exception.competition.RegistrationClosedException;
import org.youcode.hunterleague.web.exception.participation.ParticipationAlreadyExistException;
import org.youcode.hunterleague.web.exception.participation.ParticipationNotFoundException;
import org.youcode.hunterleague.web.exception.species.InvalidSpeciesException;
import org.youcode.hunterleague.web.exception.species.SpeciesNotFoundException;
import org.youcode.hunterleague.web.exception.user.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUserExeption.class)
    public ResponseEntity<String> handleInvalidUserException(InvalidUserExeption ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserNameAlreadyExistException.class)
    public ResponseEntity<String> handleUserNameAlreadyExistException(UserNameAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<String> handleEmailAlreadyExistException(EmailAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserDoesntExistException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(LicenseExpiredException.class)
    public ResponseEntity<String> handleLicenseExpiredException(LicenseExpiredException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }



    @ExceptionHandler(InvalidSpeciesException.class)
    public ResponseEntity<String> handleInvalidSpeciesException(InvalidSpeciesException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(SpeciesNotFoundException.class)
    public ResponseEntity<String> handelSpeciesNotFoundException(SpeciesNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }



    @ExceptionHandler(CompetitionAlreadyExistException.class)
    public ResponseEntity<String> handelCompetitionAlreadyExistException(CompetitionAlreadyExistException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CompetitionNotFoundException.class)
    public ResponseEntity<String> handelCompetitionNotFoundException(CompetitionNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(RegistrationClosedException.class)
    public ResponseEntity<String> handelRegistrationClosedException(RegistrationClosedException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }



    @ExceptionHandler(ParticipationAlreadyExistException.class)
    public ResponseEntity<String> handelParticipationAlreadyExistException(ParticipationAlreadyExistException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ParticipationNotFoundException.class)
    public ResponseEntity<String> handelParticipationNotFoundException(ParticipationNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    }
