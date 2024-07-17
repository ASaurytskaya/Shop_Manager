package by.fin.shop_manager.exception;

public class ProductNotFoundException extends BadRequestException {
    private static final String NO_PRODUCT_FOUND = "No product found ";

    public ProductNotFoundException(String message) {
        super(NO_PRODUCT_FOUND + message);
    }

    public ProductNotFoundException() {
        super(NO_PRODUCT_FOUND);
    }
}
