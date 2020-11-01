package com.github.xustyx.xshared.bus.infrastructure.query;

import com.github.xustyx.xshared.bus.domain.BusHandlerRegister;
import com.github.xustyx.xshared.bus.domain.query.Query;
import com.github.xustyx.xshared.bus.domain.query.QueryBus;
import com.github.xustyx.xshared.bus.domain.query.QueryHandler;
import com.github.xustyx.xshared.bus.domain.query.Response;
import com.github.xustyx.xshared.exception.ApplicationExceptions;
import com.github.xustyx.xshared.logger.domain.Logger;
import com.github.xustyx.xshared.logger.infrastructure.LoggerFactory;

import java.util.Optional;

public class QueryBusInMemory implements QueryBus {

    private final Logger logger = LoggerFactory.getLogger(QueryBusInMemory.class);

    private BusHandlerRegister busHandlerRegister;

    public QueryBusInMemory(BusHandlerRegister busHandlerRegister) {
        this.busHandlerRegister = busHandlerRegister;
    }

    @Override
    public <R extends Response> R ask(Query query) {
        String queryName = query.getClass().getInterfaces()[0].getSimpleName();
        Optional<QueryHandler<?, ?>> oQueryHandler = busHandlerRegister.search(query);

        if (oQueryHandler.isPresent()) {
            logger.info(String.format("Dispatching %s to handler", queryName));
            QueryHandler queryHandler = oQueryHandler.get();
            return (R) queryHandler.handle(query);
        }

        logger.warn(String.format("Handler was not found to dispatch %s", queryName));
        throw ApplicationExceptions.HANDLER_NOT_FOUND;
    }
}
