package com.github.xustyx.xshared.bus.domain.query;

public interface QueryHandler<Q extends Query, R extends Response> {
    R handle(Q query);
}
