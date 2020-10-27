package com.github.xustyx.xshared.bus.domain;

import com.github.xustyx.xshared.bus.domain.command.Command;
import com.github.xustyx.xshared.bus.domain.command.CommandHandler;
import com.github.xustyx.xshared.bus.domain.event.Event;
import com.github.xustyx.xshared.bus.domain.event.EventHandler;
import com.github.xustyx.xshared.bus.domain.query.Query;
import com.github.xustyx.xshared.bus.domain.query.QueryHandler;
import com.github.xustyx.xshared.bus.domain.query.Response;

import java.util.List;
import java.util.Optional;

public interface BusHandlerRegister {

    void register(Class<? extends Query> clazz, QueryHandler<? extends Query, ? extends Response> queryHandler);

    void register(Class<? extends Command> clazz, CommandHandler<? extends Command> commandHandler);

    void register(Class<? extends Event> clazz, List<EventHandler<? extends Event>> eventHandlers);

    Optional<QueryHandler<?, ?>> search(Query query);

    Optional<CommandHandler<?>> search(Command command);

    List<EventHandler<? extends Event>> search(Event event);
}
