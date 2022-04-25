package Exceptions;

public class OversizeException extends Exception {
    private static final long serialVersionUID = 1L;

    public OversizeException(String errorMessage) {
        super(errorMessage);
    }
}

