package com.github.xustyx.xshared.identifier.infrastructure;

import com.github.xustyx.xshared.identifier.domain.Identifier;
import com.github.xustyx.xshared.identifier.domain.IdentifierGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdentifierJavaGenerator implements IdentifierGenerator {
    @Override
    public Identifier generate() {
        return Identifier.create(UUID.randomUUID().toString());
    }
}
