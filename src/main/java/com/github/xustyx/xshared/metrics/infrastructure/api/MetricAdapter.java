package com.github.xustyx.xshared.metrics.infrastructure.api;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.xustyx.xshared.metrics.domain.Metric;

@JsonPropertyOrder({"success", "warn", "fail", "total"})
public class MetricAdapter {
    private Metric metric;

    public MetricAdapter(Metric metric) {
        this.metric = metric;
    }

    public int getSuccess() {
        return metric.success();
    }

    public int getWarn() {
        return metric.warn();
    }

    public int getFail() {
        return metric.fail();
    }

    public int getTotal() {
        return metric.total();
    }
}
