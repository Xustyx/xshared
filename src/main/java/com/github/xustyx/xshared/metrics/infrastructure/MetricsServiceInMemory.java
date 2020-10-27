package com.github.xustyx.xshared.metrics.infrastructure;

import com.github.xustyx.xshared.metrics.application.MetricCountCommand;
import com.github.xustyx.xshared.metrics.application.MetricsResponse;
import com.github.xustyx.xshared.metrics.application.MetricsService;
import com.github.xustyx.xshared.metrics.domain.MetricBusType;
import com.github.xustyx.xshared.metrics.domain.MetricType;
import com.github.xustyx.xshared.metrics.domain.Metrics;
import org.springframework.stereotype.Service;

@Service
public class MetricsServiceInMemory implements MetricsService {
    private Metrics metrics;

    public MetricsServiceInMemory() {
        this.metrics = Metrics.create();
    }

    @Override
    public void countMetric(MetricCountCommand metricCountCommand) {

        if (MetricType.SUCCESS.name().equalsIgnoreCase(metricCountCommand.type())) {
            countSuccess(MetricBusType.valueOf(metricCountCommand.busType()), metricCountCommand.name());
        }

        if (MetricType.WARN.name().equalsIgnoreCase(metricCountCommand.type())) {
            countWarn(MetricBusType.valueOf(metricCountCommand.busType()), metricCountCommand.name());
        }

        if (MetricType.FAIL.name().equalsIgnoreCase(metricCountCommand.type())) {
            countFail(MetricBusType.valueOf(metricCountCommand.busType()), metricCountCommand.name());
        }

    }

    @Override
    public MetricsResponse list() {
        return MetricsResponseMapper.from(metrics);
    }

    private void countSuccess(MetricBusType type, String key) {
        metrics.countSuccess(type, key);
    }

    private void countWarn(MetricBusType type, String key) {
        metrics.countWarn(type, key);
    }

    private void countFail(MetricBusType type, String key) {
        metrics.countFail(type, key);
    }

}
