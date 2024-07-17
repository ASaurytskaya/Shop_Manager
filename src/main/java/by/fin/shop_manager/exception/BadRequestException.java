package by.fin.shop_manager.exception;

public class BadRequestException extends RuntimeException {
    private static final String MESSAGE = "BAD REQUEST\n";

    public BadRequestException(String message) {
        super(MESSAGE + message);
    }
}
