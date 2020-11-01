package com.github.xustyx.xshared.bus.infrastructure.command;

import com.github.xustyx.xshared.bus.domain.BusHandlerRegister;
import com.github.xustyx.xshared.bus.domain.command.Command;
import com.github.xustyx.xshared.bus.domain.command.CommandBus;
import com.github.xustyx.xshared.bus.domain.command.CommandHandler;
import com.github.xustyx.xshared.exception.ApplicationExceptions;
import com.github.xustyx.xshared.logger.domain.Logger;
import com.github.xustyx.xshared.logger.infrastructure.LoggerFactory;

import java.util.Optional;

public class CommandBusInMemory implements CommandBus {

    private final Logger logger = LoggerFactory.getLogger(CommandBusInMemory.class);

    private BusHandlerRegister busHandlerRegister;

    public CommandBusInMemory(BusHandlerRegister busHandlerRegister) {
        this.busHandlerRegister = busHandlerRegister;
    }

    @Override
    public void dispatch(Command command) {
        String commandName = command.getClass().getInterfaces()[0].getSimpleName();
        Optional<CommandHandler<?>> oCommandHandler = busHandlerRegister.search(command);

        if (oCommandHandler.isPresent()) {
            logger.info(String.format("Dispatching %s to handler", commandName));
            CommandHandler commandHandler = oCommandHandler.get();
            commandHandler.handle(command);
        } else {
            logger.warn(String.format("Handler was not found to dispatch %s", commandName));
            throw ApplicationExceptions.HANDLER_NOT_FOUND;
        }
    }
}
