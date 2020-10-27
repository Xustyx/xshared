package com.github.xustyx.xshared.exception;

public class ApplicationException extends RuntimeException {

    private final String code;
    private final ApplicationExceptionCategory category;
    private final String description;

    private ApplicationException(String code, ApplicationExceptionCategory category, String description) {
        this.code = code;
        this.category = category;
        this.description = description;
    }

    public static ApplicationException create(String code, ApplicationExceptionCategory category, String description){
        return new ApplicationException(code,category,description);
    }

    public String getCode() {
        return code;
    }

    public ApplicationExceptionCategory getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getMessage() {
        return String.format("[%s](%s): %s", category, code, description);
    }

}
