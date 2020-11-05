package com.github.xustyx.xshared.api.infrastructure;

import com.github.xustyx.xshared.bus.domain.command.Command;
import com.github.xustyx.xshared.bus.domain.command.CommandBus;
import com.github.xustyx.xshared.identifier.domain.Identifier;
import com.github.xustyx.xshared.identifier.domain.IdentifierGenerator;

public abstract class CommandController {

    private final CommandBus commandBus;
    private final IdentifierGenerator identifierGenerator;

    protected CommandController(CommandBus commandBus, IdentifierGenerator identifierGenerator) {
        this.commandBus = commandBus;
        this.identifierGenerator = identifierGenerator;
    }

    protected void dispatch(Command command) {
        this.commandBus.dispatch(command);
    }

    protected Identifier identifier() {
        return this.identifierGenerator.generate();
    }
}
