package iuh.fit.boshop.exceptions;

public class ProductVariantNotFoundException extends RuntimeException {
    public ProductVariantNotFoundException(String message) {
        super(message);
    }
}
