package iuh.fit.boshop.dto.response;

public record ApiResponse<T>(
        String message,
        T data
) {
}
