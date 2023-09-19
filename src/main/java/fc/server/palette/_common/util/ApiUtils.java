package fc.server.palette._common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ApiUtils {
    public static <T> ApiResult<T> success(HttpStatus status, T response) {
        return new ApiResult<>(status.value(), true, response, null);
    }

    public static <T> ApiResult<T> error(T message, HttpStatus status) {
        return new ApiResult<>(status.value(), false,null, new ApiError(message));
    }

    @Getter
    @AllArgsConstructor
    public static class ApiResult<T> {
        private final int status;
        private final boolean success;
        private final T response;
        private final ApiError error;
    }

    @Getter
    @AllArgsConstructor
    public static class ApiError<T> {
        private final T message;
    }
}