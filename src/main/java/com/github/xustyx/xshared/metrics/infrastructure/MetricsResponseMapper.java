package com.github.xustyx.xshared.metrics.infrastructure;


import com.github.xustyx.xshared.metrics.application.MetricResponse;
import com.github.xustyx.xshared.metrics.application.MetricsResponse;
import com.github.xustyx.xshared.metrics.domain.Metric;
import com.github.xustyx.xshared.metrics.domain.Metrics;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MetricsResponseMapper {
    static MetricsResponse from(Metrics metrics) {
        return new MetricsResponse() {
            @Override
            public Set<MetricResponse> commands() {
                return metricResponseMapper(metrics.commands());
            }

            @Override
            public Set<MetricResponse> queries() {
                return metricResponseMapper(metrics.queries());
            }

            @Override
            public Set<MetricResponse> events() {
                return metricResponseMapper(metrics.events());

            }
        };
    }

    private static Set<MetricResponse> metricResponseMapper(Map<String, Metric> metrics) {
        return metrics
                .entrySet()
                .stream()
                .map(MetricResponseMapper::from)
                .collect(Collectors.toSet());
    }
}
