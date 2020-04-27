package glaucus.api.web.utils;


import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import glaucus.api.domain.common.ApiResult;
import glaucus.api.web.constants.ApiResponseConstants;

public class ResponseEntityUtils {

    public static <T> ResponseEntity<ApiResult<T>> responseEntity(String resultMessage, T data) {
        return responseEntity(ApiResult.of(ApiResponseConstants.API_RES_KEY_RESULT, resultMessage, data));
    }

    public static <T> ResponseEntity<ApiResult<T>> getResponse(String result, String resultMessage, T data) {
        return responseEntity(ApiResult.of(result, resultMessage, data));
    }

    public static <T> ResponseEntity<ApiResult<T>> responseEntity(T data) {
        if (data instanceof Map) {
            Map<String, Object> dataMap = (Map<String, Object>) data;
            String result = MapUtils.getString(dataMap, ApiResponseConstants.API_RES_KEY_RESULT);
            String message = MapUtils.getString(dataMap, ApiResponseConstants.API_RES_KEY_RESULT_MESSAGE);

            if (StringUtils.equalsIgnoreCase(result, ApiResponseConstants.API_KEY_RESULT_VALUE_FAIL)) {
                return getResponse(result, message, null);
            }
        }

        return responseEntity(ApiResult.success(data));
    }

    public static <T> ResponseEntity<ApiResult<T>> responseEntity(T data, HttpStatus httpStatus) {
        return responseEntity(ApiResult.success(data), httpStatus);
    }

    public static <T> ResponseEntity<ApiResult<T>> getResponse() {
        return responseEntity(ApiResult.success());
    }

    public static <T> ResponseEntity<ApiResult<T>> errorResponseEntity() {
        return errorResponseEntity("Something wrong happened. Please try it again.");
    }

    public static <T> ResponseEntity<ApiResult<T>> errorResponseEntity(String message) {
        return responseEntity(ApiResult.failure(message));
    }

    public static <T> ResponseEntity<ApiResult<T>> errorResponseEntity(String message, T data) {
        return responseEntity(ApiResult.failure(message, data));
    }

    private static <T> ResponseEntity<ApiResult<T>> responseEntity(ApiResult<T> apiResult) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(apiResult, responseHeaders, HttpStatus.OK);
    }

    private static <T> ResponseEntity<ApiResult<T>> responseEntity(ApiResult<T> apiResult, HttpStatus httpStatus) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(apiResult, responseHeaders, httpStatus);
    }
}