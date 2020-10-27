package com.github.xustyx.xshared.bus.infrastructure.event;

import com.github.xustyx.xshared.bus.domain.event.Event;
import com.github.xustyx.xshared.bus.domain.event.EventBus;
import com.github.xustyx.xshared.exception.ApplicationException;
import com.github.xustyx.xshared.metrics.application.MetricCountCommand;
import com.github.xustyx.xshared.metrics.application.MetricsService;

import java.util.List;

public class EventBusMetric implements EventBus {
    private EventBus eventBus;
    private MetricsService metricsService;

    public EventBusMetric(EventBus eventBus, MetricsService metricsService) {
        this.eventBus = eventBus;
        this.metricsService = metricsService;
    }

    @Override
    public void publish(List<Event> events) {
        events.forEach(this::publish);
    }

    @Override
    public void publish(Event event) {
        String eventName = event.getClass().getInterfaces()[0].getSimpleName();

        try {
            eventBus.publish(event);
            metricsService.countMetric(MetricCountCommand.successEvent(eventName));
        } catch (Exception e) {
            if (e instanceof ApplicationException) {
                metricsService.countMetric(MetricCountCommand.warnEvent(eventName));
            } else {
                metricsService.countMetric(MetricCountCommand.failEvent(eventName));
            }
            throw e;
        }
    }


}
