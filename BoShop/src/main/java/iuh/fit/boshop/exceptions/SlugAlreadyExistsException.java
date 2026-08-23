package iuh.fit.boshop.exceptions;

public class SlugAlreadyExistsException extends RuntimeException {
    public SlugAlreadyExistsException(String message) {
        super(message);
    }
}
