package cz.sharee.backend.exceptions.domain;

public class UsernameAlreadyExistsException extends Exception{
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
