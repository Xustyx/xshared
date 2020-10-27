package com.github.xustyx.xshared.bus.domain.event;

import java.util.List;

public interface EventBus {
    void publish(final List<Event> events);

    void publish(final Event event);
}
