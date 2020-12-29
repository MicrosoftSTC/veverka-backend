package cz.sharee.backend.exceptions.domain;

public class BadRequestException extends Exception{
    public BadRequestException(String message) {
        super(message);
    }
}
