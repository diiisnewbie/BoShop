package iuh.fit.boshop.exceptions;

public class SkuAlreadyExistsException extends RuntimeException {
    public SkuAlreadyExistsException(String message) {
        super(message);
    }
}
