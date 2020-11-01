package com.github.xustyx.xshared.bus.domain.event;

import java.util.ArrayList;
import java.util.List;

public class AggregateRoot {
    private List<Event> events = new ArrayList<>();

    public final List<Event> pullEvents() {
        List<Event> pEvents = new ArrayList<>(this.events);
        this.events.clear();

        return pEvents;
    }

    protected final void record(Event event){
        this.events.add(event);
    }
}
