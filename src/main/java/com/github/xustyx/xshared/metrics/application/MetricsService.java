package com.github.xustyx.xshared.metrics.application;

public interface MetricsService {
    void countMetric(MetricCountCommand metricCountCommand);
    MetricsResponse list();
}
