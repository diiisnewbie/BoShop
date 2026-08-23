package iuh.fit.boshop.exceptions;

import iuh.fit.boshop.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(ex.getMessage(), null));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadCredentials(
            BadCredentialsException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse<>(ex.getMessage(), null));
    }

    @ExceptionHandler(SlugAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleSlugAlreadyExists(
            SlugAlreadyExistsException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(ex.getMessage(), null));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleProductNotFound(
            ProductNotFoundException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(ex.getMessage(), null));
    }

    @ExceptionHandler(SkuAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleSkuAlreadyExists(
            SkuAlreadyExistsException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(ex.getMessage(), null));
    }

    @ExceptionHandler(ProductVariantNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleProductVariantNotFound(
            ProductVariantNotFoundException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(ex.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(
            Exception ex
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ApiResponse<>(
                                "Internal server error",
                                null
                        )
                );
    }

}
