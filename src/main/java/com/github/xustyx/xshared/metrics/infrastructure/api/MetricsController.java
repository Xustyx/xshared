package com.github.xustyx.xshared.metrics.infrastructure.api;

import com.github.xustyx.xshared.metrics.application.MetricsService;

public abstract class MetricsController<R> {

    private MetricsService metricsService;

    protected MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    public abstract R response();

    protected MetricsResponseAdapter metrics() {
        return new MetricsResponseAdapter(metricsService.list());
    }
}
