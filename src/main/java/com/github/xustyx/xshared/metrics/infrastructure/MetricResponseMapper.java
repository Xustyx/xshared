package com.github.xustyx.xshared.metrics.infrastructure;

import com.github.xustyx.xshared.metrics.application.MetricResponse;
import com.github.xustyx.xshared.metrics.domain.Metric;

import java.util.Map;

public class MetricResponseMapper {
    static MetricResponse from(Map.Entry<String, Metric> metric){
        return new MetricResponse() {
            @Override
            public String name() {
                return metric.getKey();
            }

            @Override
            public Metric metric() {
                return metric.getValue();
            }
        };
    }
}
