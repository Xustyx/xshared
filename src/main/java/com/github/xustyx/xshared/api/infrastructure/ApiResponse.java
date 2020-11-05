package com.github.xustyx.xshared.api.infrastructure;

import com.github.xustyx.xshared.exception.ApplicationException;
import com.github.xustyx.xshared.logger.domain.Logger;
import com.github.xustyx.xshared.logger.infrastructure.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class ApiResponse {

    private static final Logger logger = LoggerFactory.getLogger(ApiResponse.class);

    public static final String UNHANDLED_EXCEPTION = "UNHANDLED_EXCEPTION";
    private final String code;
    private final String category;
    private final String message;


    public ApiResponse(String code, String category, String message) {
        this.code = code;
        this.category = category;
        this.message = message;
    }

    public static ResponseEntity ok(Object obj) {
        return ResponseEntity.ok(obj);
    }

    public static ResponseEntity created(String path, String id) {
        UriComponents uriComponents = UriComponentsBuilder.fromPath(path).buildAndExpand(id);
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    public static ResponseEntity accepted() {
        return ResponseEntity.accepted().build();
    }

    public static ResponseEntity exception(Exception e) {
        return ResponseEntity
                .status(ApiResponse.status(e))
                .body(ApiResponse.from(e));
    }

    protected static HttpStatus status(Exception exception) {
        if (exception instanceof ApplicationException) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    protected static ApiResponse from(ApplicationException applicationException) {
        return new ApiResponse(applicationException.getCode(), applicationException.getCategory().name(), applicationException.getDescription());
    }

    protected static ApiResponse from(Exception exception) {
        logger.error(exception.getMessage(), exception);
        if (exception instanceof ApplicationException) {
            return ApiResponse.from((ApplicationException) exception);
        }
        return new ApiResponse(UNHANDLED_EXCEPTION, "INTERNAL", exception.getMessage());
    }

    public String getCode() {
        return code;
    }

    public String getCategory() {
        return category;
    }

    public String getMessage() {
        return message;
    }
}
