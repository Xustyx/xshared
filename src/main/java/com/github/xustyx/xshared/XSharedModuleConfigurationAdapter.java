package com.github.xustyx.xshared;

import com.github.xustyx.xshared.bus.domain.BusHandlerRegister;
import com.github.xustyx.xshared.bus.domain.command.CommandBus;
import com.github.xustyx.xshared.bus.domain.event.EventBus;
import com.github.xustyx.xshared.bus.domain.query.QueryBus;
import com.github.xustyx.xshared.bus.infrastructure.BusHandlerRegisterInMemory;
import com.github.xustyx.xshared.bus.infrastructure.command.CommandBusInMemory;
import com.github.xustyx.xshared.bus.infrastructure.command.CommandBusMetric;
import com.github.xustyx.xshared.bus.infrastructure.event.EventBusInMemory;
import com.github.xustyx.xshared.bus.infrastructure.event.EventBusMetric;
import com.github.xustyx.xshared.bus.infrastructure.query.QueryBusInMemory;
import com.github.xustyx.xshared.bus.infrastructure.query.QueryBusMetric;
import com.github.xustyx.xshared.identifier.domain.IdentifierGenerator;
import com.github.xustyx.xshared.identifier.infrastructure.IdentifierJavaGenerator;
import com.github.xustyx.xshared.metrics.application.MetricsService;
import com.github.xustyx.xshared.metrics.infrastructure.MetricsServiceInMemory;
import org.springframework.context.annotation.Bean;

public abstract class XSharedModuleConfigurationAdapter {

    private MetricsService metricsService;
    private BusHandlerRegister busHandlerRegister;
    private CommandBus commandBus;
    private QueryBus queryBus;
    private EventBus eventBus;

    private IdentifierGenerator identifierGenerator;

    public XSharedModuleConfigurationAdapter() {
        this.metricsService = new MetricsServiceInMemory();
        this.busHandlerRegister = new BusHandlerRegisterInMemory();
        this.commandBus = new CommandBusInMemory(busHandlerRegister);
        this.queryBus = new QueryBusInMemory(busHandlerRegister);
        this.eventBus = new EventBusInMemory(busHandlerRegister);
        this.identifierGenerator = new IdentifierJavaGenerator();
    }

    @Bean
    public IdentifierGenerator identifierGenerator(){
        return this.identifierGenerator;
    }

    @Bean
    public BusHandlerRegister busHandlerRegister() {
        return this.busHandlerRegister;
    }

    @Bean
    public MetricsService metricsService() {
        return this.metricsService;
    }

    @Bean
    public CommandBus commandBus() {
        if (withMetricsModule()) {
            return new CommandBusMetric(this.commandBus, this.metricsService);
        }

        return this.commandBus;
    }

    @Bean
    public QueryBus queryBus() {
        if (withMetricsModule()) {
            return new QueryBusMetric(this.queryBus, this.metricsService);
        }

        return this.queryBus;
    }

    @Bean
    public EventBus eventBus() {
        if (withMetricsModule()) {
            return new EventBusMetric(this.eventBus, this.metricsService);
        }

        return this.eventBus;
    }


    public abstract boolean withMetricsModule();

}
