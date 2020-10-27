package com.github.xustyx.xshared.logger.infrastructure;

import com.github.xustyx.xshared.logger.domain.Logger;

public class LoggerFactory {
    public static Logger getLogger(Class<?> clazz) {
        return Log4j2Wrapper.create(clazz);
    }
}
