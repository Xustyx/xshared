package com.github.xustyx.xshared.bus.domain.command;

public interface CommandBus {
    void dispatch(Command command);
}
