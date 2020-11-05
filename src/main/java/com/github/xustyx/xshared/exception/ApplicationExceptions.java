package com.github.xustyx.xshared.exception;

public class ApplicationExceptions {

    private ApplicationExceptions() {
        throw new IllegalStateException("Utility class");
    }

    public static final ApplicationException INVALID_IDENTIFIER = ApplicationException.create("INVALID_IDENTIFIER", ApplicationExceptionCategory.FORMAT, "Invalid identifier uuid format");
    public static final ApplicationException HANDLER_NOT_FOUND = ApplicationException.create("HANDLER_NOT_FOUND", ApplicationExceptionCategory.APPLICATION, "Handler was not found to dispatch the command or query");
}
