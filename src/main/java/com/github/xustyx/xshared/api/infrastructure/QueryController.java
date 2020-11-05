package com.github.xustyx.xshared.api.infrastructure;

import com.github.xustyx.xshared.bus.domain.query.Query;
import com.github.xustyx.xshared.bus.domain.query.QueryBus;
import com.github.xustyx.xshared.bus.domain.query.Response;

public abstract class QueryController {
    private final QueryBus queryBus;

    protected QueryController(QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    public Response ask(Query query){
        return this.queryBus.ask(query);
    }
}
