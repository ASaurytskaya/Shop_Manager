package by.fin.shop_manager.exception;

public class ImageFileNotFoundException extends BadRequestException {
    private static final String NO_FILE_FOUND = "Image file not found ";

    public ImageFileNotFoundException(String message) {
        super(NO_FILE_FOUND + message);
    }

    public ImageFileNotFoundException() {
        super(NO_FILE_FOUND);
    }
}
