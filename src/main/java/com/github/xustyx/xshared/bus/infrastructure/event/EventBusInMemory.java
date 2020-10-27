package com.github.xustyx.xshared.bus.infrastructure.event;

import com.github.xustyx.xshared.bus.domain.BusHandlerRegister;
import com.github.xustyx.xshared.bus.domain.event.Event;
import com.github.xustyx.xshared.bus.domain.event.EventBus;
import com.github.xustyx.xshared.bus.domain.event.EventHandler;
import com.github.xustyx.xshared.logger.domain.Logger;
import com.github.xustyx.xshared.logger.infrastructure.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventBusInMemory implements EventBus {

    private final Logger logger = LoggerFactory.getLogger(EventBusInMemory.class);

    private BusHandlerRegister busHandlerRegister;

    public EventBusInMemory(BusHandlerRegister busHandlerRegister) {
        this.busHandlerRegister = busHandlerRegister;
    }

    @Override
    public void publish(List<Event> events) {
        events.forEach(this::publish);
    }

    @Override
    public void publish(Event event) {
        List<EventHandler<? extends Event>> eventHandlers = busHandlerRegister.search(event);
        String eventName = event.getClass().getInterfaces()[0].getSimpleName();

        if (eventHandlers != null) {
            logger.info(String.format("Dispatching %s to handlers", eventName));
            for (EventHandler eventHandler : eventHandlers) {
                eventHandler.handle(event);
            }
        }
    }
}
