package com.github.xustyx.xshared.bus.domain.event;

import java.util.ArrayList;
import java.util.List;

public class AggregateRoot {
    private List<Event> events = new ArrayList<>();

    final public List<Event> pullEvents() {
        List<Event> events = new ArrayList<>(this.events);
        this.events.clear();

        return events;
    }

    final protected void record(Event event){
        this.events.add(event);
    }
}
