package com.github.xustyx.xshared.bus.domain.event;

public interface EventHandler<E extends Event> {
    void handle(E event);
}