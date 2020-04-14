package Shop.exceptions;

public class PositionException extends RuntimeException {
    public PositionException(String message)
    {
        super((message));
    }
}