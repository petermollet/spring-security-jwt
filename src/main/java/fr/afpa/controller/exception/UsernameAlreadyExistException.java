package fr.afpa.controller.exception;

public class UsernameAlreadyExistException extends RuntimeException {

    public UsernameAlreadyExistException() {
        super("Username already exist");
    }

}
