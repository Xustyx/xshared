package com.github.xustyx.xshared.metrics.infrastructure.api;


import com.github.xustyx.xshared.metrics.application.MetricResponse;

public class MetricResponseAdapter {
    private MetricResponse metricResponse;

    public MetricResponseAdapter(MetricResponse metricResponse) {
        this.metricResponse = metricResponse;
    }

    public String getName() {
        return metricResponse.name();
    }

    public MetricAdapter getMetric() {
        return new MetricAdapter(metricResponse.metric());
    }
}
