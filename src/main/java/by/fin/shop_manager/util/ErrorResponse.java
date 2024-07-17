package by.fin.shop_manager.util;

public class ErrorResponse {
    private String errorType;
    private String message;

    public ErrorResponse() {}

    public ErrorResponse(String errorType, String message) {
        this.errorType = errorType;
        this.message = message;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
