package com.github.xustyx.xshared.bus.domain.command;

public interface CommandHandler<C extends Command> {
    void handle(C command);
}
