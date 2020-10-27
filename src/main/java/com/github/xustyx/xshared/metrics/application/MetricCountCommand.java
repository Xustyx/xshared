package com.github.xustyx.xshared.metrics.application;

import com.github.xustyx.xshared.metrics.domain.MetricBusType;
import com.github.xustyx.xshared.metrics.domain.MetricType;

public interface MetricCountCommand {
    String busType();

    String name();

    String type();

    static MetricCountCommand successCommand(String name) {
        return MetricCountCommand.create(MetricBusType.COMMAND.name(), name, MetricType.SUCCESS.name());
    }

    static MetricCountCommand successQuery(String name) {
        return MetricCountCommand.create(MetricBusType.QUERY.name(), name, MetricType.SUCCESS.name());
    }

    static MetricCountCommand successEvent(String name) {
        return MetricCountCommand.create(MetricBusType.EVENT.name(), name, MetricType.SUCCESS.name());
    }

    static MetricCountCommand warnCommand(String name) {
        return MetricCountCommand.create(MetricBusType.COMMAND.name(), name, MetricType.WARN.name());
    }

    static MetricCountCommand warnQuery(String name) {
        return MetricCountCommand.create(MetricBusType.QUERY.name(), name, MetricType.WARN.name());
    }

    static MetricCountCommand warnEvent(String name) {
        return MetricCountCommand.create(MetricBusType.EVENT.name(), name, MetricType.WARN.name());
    }

    static MetricCountCommand failCommand(String name) {
        return MetricCountCommand.create(MetricBusType.COMMAND.name(), name, MetricType.FAIL.name());
    }

    static MetricCountCommand failQuery(String name) {
        return MetricCountCommand.create(MetricBusType.QUERY.name(), name, MetricType.FAIL.name());
    }

    static MetricCountCommand failEvent(String name) {
        return MetricCountCommand.create(MetricBusType.EVENT.name(), name, MetricType.FAIL.name());
    }

    private static MetricCountCommand create(String busType, String name, String type) {
        return new MetricCountCommand() {
            @Override
            public String busType() {
                return busType;
            }

            @Override
            public String name() {
                return name;
            }

            @Override
            public String type() {
                return type;
            }
        };
    }
}
