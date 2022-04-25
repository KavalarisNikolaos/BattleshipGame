package Exceptions;

public class InvalidCountException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidCountException(String errorMessage) {
        super(errorMessage);
    }
}