package Exceptions;

public class OverlapTilesException extends Exception {
    private static final long serialVersionUID = 1L;

    public OverlapTilesException(String errorMessage) {
        super(errorMessage);
    }
}
