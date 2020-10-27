package com.github.xustyx.xshared.bus.infrastructure.command;

import com.github.xustyx.xshared.bus.domain.command.Command;
import com.github.xustyx.xshared.bus.domain.command.CommandBus;
import com.github.xustyx.xshared.exception.ApplicationException;
import com.github.xustyx.xshared.metrics.application.MetricCountCommand;
import com.github.xustyx.xshared.metrics.application.MetricsService;

public class CommandBusMetric implements CommandBus {

    private CommandBus commandBus;
    private MetricsService metricsService;

    public CommandBusMetric(CommandBus commandBus, MetricsService metricsService) {
        this.commandBus = commandBus;
        this.metricsService = metricsService;
    }

    @Override
    public void dispatch(Command command) {
        String commandName = command.getClass().getInterfaces()[0].getSimpleName();

        try {
            commandBus.dispatch(command);
            metricsService.countMetric(MetricCountCommand.successCommand(commandName));
        } catch (Exception e) {
            if (e instanceof ApplicationException) {
                metricsService.countMetric(MetricCountCommand.warnCommand(commandName));
            } else {
                metricsService.countMetric(MetricCountCommand.failCommand(commandName));
            }
            throw e;
        }
    }
}
