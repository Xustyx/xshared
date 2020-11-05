package com.github.xustyx.xshared.identifier;

import com.github.xustyx.xshared.exception.ApplicationException;
import com.github.xustyx.xshared.identifier.domain.Identifier;
import com.github.xustyx.xshared.identifier.domain.IdentifierGenerator;
import com.github.xustyx.xshared.identifier.infrastructure.IdentifierJavaGenerator;
import com.github.xustyx.xshared.logger.domain.Logger;
import com.github.xustyx.xshared.logger.infrastructure.LoggerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;


import static org.junit.jupiter.api.Assertions.*;

class IdentifierTest {

    private final Logger logger = LoggerFactory.getLogger(IdentifierTest.class);

    private IdentifierGenerator identifierGenerator;

    @BeforeEach
    protected void setUp() {
        this.identifierGenerator = new IdentifierJavaGenerator();
    }

    @Test
    void create_valid_identifier() {
        final String uuid = "04003b9c-3fb0-4ff2-b50a-15b73586018c";
        final Identifier identifier = Identifier.create(uuid);

        logger.info(String.format("Testing valid identifier with: %s", uuid));

        assertEquals(identifier.value(), uuid);
    }

    @Test
    void generate_valid_identifier() {
        final Identifier identifier = identifierGenerator.generate();

        assertTrue(StringUtils.isNotBlank(identifier.value()));
    }

    @Test
    void create_invalid_identifier_throws_identifier_invalid_value_exception() {
        final String uuid = "asdasdasd";
        final String expectedMessage = "[FORMAT](INVALID_IDENTIFIER): Invalid identifier uuid format";

        Exception exception = assertThrows(ApplicationException.class, () -> Identifier.create(uuid));
        final String actualMessage = exception.getMessage();

        logger.info(String.format("Testing invalid identifier with: %s", uuid));

        assertEquals(expectedMessage, actualMessage);
    }
}
