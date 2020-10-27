package com.github.xustyx.xshared.logger.infrastructure;

import com.github.xustyx.xshared.logger.domain.Logger;
import org.apache.logging.log4j.LogManager;

public class Log4j2Wrapper implements Logger {

    private final org.apache.logging.log4j.Logger logger;

    private Log4j2Wrapper(org.apache.logging.log4j.Logger logger) {
        this.logger = logger;
    }

    protected static Logger create(Class<?> clazz) {
        return new Log4j2Wrapper(LogManager.getLogger(clazz));
    }

    @Override
    public void info(String msg) {
        logger.info(msg);
    }

    @Override
    public void info(String msg, Object... args) {
        logger.info(msg, args);
    }

    @Override
    public void error(String format, Exception ex) {
        logger.error(format, ex);
    }

    @Override
    public void warn(String msg) {
        logger.warn(msg);
    }

    @Override
    public void warn(String msg, Object... args) {
        logger.warn(msg, args);
    }

    @Override
    public void debug(String msg) {
        logger.debug(msg);
    }

    @Override
    public void error(String msg) {
        logger.error(msg);
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

}
