package com.github.xustyx.xshared.metrics.infrastructure.api;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.xustyx.xshared.metrics.application.MetricsResponse;

import java.util.Set;
import java.util.stream.Collectors;

@JsonPropertyOrder({"summary", "commands", "queries", "events"})
public class MetricsResponseAdapter {

    private MetricsResponse metricsResponse;

    public MetricsResponseAdapter(MetricsResponse metricsResponse) {
        this.metricsResponse = metricsResponse;
    }

    public Set<MetricResponseAdapter> getCommands() {
        return metricsResponse.commands().stream().map(MetricResponseAdapter::new).collect(Collectors.toSet());
    }

    public Set<MetricResponseAdapter> getQueries() {
        return metricsResponse.queries().stream().map(MetricResponseAdapter::new).collect(Collectors.toSet());
    }

    public Set<MetricResponseAdapter> getEvents() {
        return metricsResponse.events().stream().map(MetricResponseAdapter::new).collect(Collectors.toSet());
    }

    public MetricsSummaryResponseAdapter getSummary() {
        return new MetricsSummaryResponseAdapter(metricsResponse);
    }
}
