package com.github.xustyx.xshared.metrics.infrastructure.api;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.xustyx.xshared.metrics.application.MetricResponse;

import java.util.Set;

@JsonPropertyOrder({"success", "warn", "fail", "total"})
public class MetricSummaryResponseAdapter {

    private Set<MetricResponse> metrics;

    public MetricSummaryResponseAdapter(Set<MetricResponse> metrics) {
        this.metrics = metrics;
    }

    public int getSuccess() {
        return metrics.stream().map(metricResponse -> metricResponse.metric().success()).mapToInt(Integer::intValue).sum();
    }

    public int getWarn() {
        return metrics.stream().map(metricResponse -> metricResponse.metric().warn()).mapToInt(Integer::intValue).sum();
    }

    public int getFail() {
        return metrics.stream().map(metricResponse -> metricResponse.metric().fail()).mapToInt(Integer::intValue).sum();
    }

    public int getTotal() {
        return metrics.stream().map(metricResponse -> metricResponse.metric().total()).mapToInt(Integer::intValue).sum();
    }
}
