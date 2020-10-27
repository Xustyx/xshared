package com.github.xustyx.xshared.exception;

public class ApplicationExceptions {
    public static final ApplicationException INVALID_IDENTIFIER = ApplicationException.create("1000", ApplicationExceptionCategory.FORMAT, "Invalid identifier uuid format");
    public static final ApplicationException HANDLER_NOT_FOUND = ApplicationException.create("1001", ApplicationExceptionCategory.APPLICATION, "Handler was not found to dispatch the command or query");
}
