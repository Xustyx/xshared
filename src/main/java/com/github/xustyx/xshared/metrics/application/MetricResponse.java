package com.github.xustyx.xshared.metrics.application;

import com.github.xustyx.xshared.metrics.domain.Metric;

public interface MetricResponse {
    String name();

    Metric metric();
}
