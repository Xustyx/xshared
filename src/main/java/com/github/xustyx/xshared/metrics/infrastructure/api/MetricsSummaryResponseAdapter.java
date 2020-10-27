package com.github.xustyx.xshared.metrics.infrastructure.api;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.xustyx.xshared.metrics.application.MetricsResponse;

@JsonPropertyOrder({"commands", "queries", "events"})
public class MetricsSummaryResponseAdapter {
    private MetricsResponse metricsResponse;

    public MetricsSummaryResponseAdapter(MetricsResponse metricsResponse) {
        this.metricsResponse = metricsResponse;
    }

    public MetricSummaryResponseAdapter getCommands() {
        return new MetricSummaryResponseAdapter(metricsResponse.commands());
    }

    public MetricSummaryResponseAdapter getQueries() {
        return new MetricSummaryResponseAdapter(metricsResponse.queries());
    }

    public MetricSummaryResponseAdapter getEvents() {
        return new MetricSummaryResponseAdapter(metricsResponse.events());
    }

}
