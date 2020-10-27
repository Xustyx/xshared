package com.github.xustyx.xshared.bus.infrastructure;

import com.github.xustyx.xshared.bus.domain.BusHandlerRegister;
import com.github.xustyx.xshared.bus.domain.command.Command;
import com.github.xustyx.xshared.bus.domain.command.CommandHandler;
import com.github.xustyx.xshared.bus.domain.event.Event;
import com.github.xustyx.xshared.bus.domain.event.EventHandler;
import com.github.xustyx.xshared.bus.domain.query.Query;
import com.github.xustyx.xshared.bus.domain.query.QueryHandler;
import com.github.xustyx.xshared.bus.domain.query.Response;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BusHandlerRegisterInMemory implements BusHandlerRegister {

    private Map<String, QueryHandler<? extends Query, ? extends Response>> queryHandlerMap = new HashMap<>();
    private Map<String, CommandHandler<? extends Command>> commandHandlerMap = new HashMap<>();
    private Map<String, List<EventHandler<? extends Event>>> eventHandlerMap = new HashMap<>();

    @Override
    public void register(Class<? extends Query> clazz, QueryHandler<? extends Query, ? extends Response> queryHandler) {
        this.queryHandlerMap.put(clazz.getName(), queryHandler);
    }

    @Override
    public void register(Class<? extends Command> clazz, CommandHandler<? extends Command> commandHandler) {
        this.commandHandlerMap.put(clazz.getName(), commandHandler);
    }

    @Override
    public void register(Class<? extends Event> clazz, List<EventHandler<? extends Event>> eventHandlers) {
        this.eventHandlerMap.put(clazz.getName(), eventHandlers);
    }

    @Override
    public Optional<QueryHandler<?, ?>> search(Query query) {
        return Optional.ofNullable(queryHandlerMap.get(query.getClass().getInterfaces()[0].getName()));
    }

    @Override
    public Optional<CommandHandler<?>> search(Command command) {
        return Optional.ofNullable(commandHandlerMap.get(command.getClass().getInterfaces()[0].getName()));
    }

    @Override
    public List<EventHandler<? extends Event>> search(Event event) {
        return eventHandlerMap.get(event.getClass().getInterfaces()[0].getName());
    }


}