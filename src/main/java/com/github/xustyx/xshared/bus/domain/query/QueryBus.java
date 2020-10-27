package com.github.xustyx.xshared.bus.domain.query;

public interface QueryBus {
    <R extends Response> R ask(Query query);
}
