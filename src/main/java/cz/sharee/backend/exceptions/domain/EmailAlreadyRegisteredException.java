package cz.sharee.backend.exceptions.domain;

public class EmailAlreadyRegisteredException extends Exception{
    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
