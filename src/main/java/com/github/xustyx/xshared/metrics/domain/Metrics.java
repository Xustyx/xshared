package com.github.xustyx.xshared.metrics.domain;

import java.util.HashMap;
import java.util.Map;

public class Metrics {
    private Map<String, Metric> commands;
    private Map<String, Metric> queries;
    private Map<String, Metric> events;

    private Metrics(Map<String, Metric> commands, Map<String, Metric> queries, Map<String, Metric> events) {
        this.commands = commands;
        this.queries = queries;
        this.events = events;
    }

    public static Metrics create() {
        return new Metrics(
                new HashMap<>(),
                new HashMap<>(),
                new HashMap<>()
        );
    }

    public void countSuccess(MetricBusType type, String key) {
        Metric metric = getAndCreateMetricIfMissing(type, key);
        metric.countSuccess();
    }

    public void countWarn(MetricBusType type, String key) {
        Metric metric = getAndCreateMetricIfMissing(type, key);
        metric.countWarn();
    }

    public void countFail(MetricBusType type, String key) {
        Metric metric = getAndCreateMetricIfMissing(type, key);
        metric.countFail();
    }

    public Map<String, Metric> commands() {
        return commands;
    }

    public Map<String, Metric> queries() {
        return queries;
    }

    public Map<String, Metric> events() {
        return events;
    }

    private Metric getAndCreateMetricIfMissing(MetricBusType type, String key) {
        Map<String, Metric> metrics = metricByType(type);
        return metrics.computeIfAbsent(key, s -> Metric.create());
    }

    private Map<String, Metric> metricByType(MetricBusType type) {
        Map<String, Metric> metrics;

        switch (type) {
            case EVENT:
                metrics = events;
                break;
            case QUERY:
                metrics = queries;
                break;
            case COMMAND:
            default:
                metrics = commands;
        }

        return metrics;
    }

}
