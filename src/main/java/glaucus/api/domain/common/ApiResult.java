package glaucus.api.domain.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import glaucus.api.web.constants.ApiResponseConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Data
public class ApiResult<T> {

    private String result;

    private String resultMessage;

    private T data;

    private ApiResult(String result, String resultMessage, T data) {
        this.result = result;
        this.resultMessage = resultMessage;
        this.data = data;
    }

    public static <T> ApiResult<T> success() {
        return of(ApiResponseConstants.API_KEY_RESULT_VALUE_SUCCESS, null, null);
    }

    public static <T> ApiResult<T> success(T data) {
        return of(ApiResponseConstants.API_KEY_RESULT_VALUE_SUCCESS, null, data);
    }

    public static <T> ApiResult<T> failure(String resultMessage) {
        return new ApiResult<T>(ApiResponseConstants.API_KEY_RESULT_VALUE_FAIL, resultMessage, null);
    }

    public static <T> ApiResult<T> failure(String resultMessage, T data) {
        return new ApiResult<T>(ApiResponseConstants.API_KEY_RESULT_VALUE_FAIL, resultMessage, data);
    }

    public static <T> ApiResult<T> of(String result, String resultMessage, T data) {
        return new ApiResult<>(result, resultMessage, data);
    }
}
