package com.github.xustyx.xshared.logger.domain;

public interface Logger {
    void info(String msg);
    void info(String msg, Object... args);
    void error(String format, Exception ex);
    void warn(String msg);
    void warn(String msg, Object... args);
    void debug(String msg);
    void error(String msg);
    boolean isInfoEnabled();



}
