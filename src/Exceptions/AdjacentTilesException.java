package Exceptions;

public class AdjacentTilesException extends Exception {
    private static final long serialVersionUID = 1L;

    public AdjacentTilesException(String errorMessage) {
        super(errorMessage);
    }
}
