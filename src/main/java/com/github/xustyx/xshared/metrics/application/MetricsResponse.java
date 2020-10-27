package com.github.xustyx.xshared.metrics.application;

import java.util.Set;

public interface MetricsResponse {
    Set<MetricResponse> commands();

    Set<MetricResponse> queries();

    Set<MetricResponse> events();
}
