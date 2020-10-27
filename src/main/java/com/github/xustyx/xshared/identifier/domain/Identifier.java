package com.github.xustyx.xshared.identifier.domain;

import com.github.xustyx.xshared.exception.ApplicationExceptions;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Identifier implements Serializable {
    private String value;

    protected Identifier(String uuid) {
        validUuidGuard(uuid);
        this.value = uuid;
    }

    public String value() {
        return value;
    }

    public static Identifier create(String uuid) {
        return new Identifier(uuid);
    }

    private void validUuidGuard(String uuid) {
        try {
            UUID.fromString(uuid);
        } catch (Exception e) {
            throw ApplicationExceptions.INVALID_IDENTIFIER;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identifier that = (Identifier) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
