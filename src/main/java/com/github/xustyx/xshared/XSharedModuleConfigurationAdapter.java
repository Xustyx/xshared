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

    private final MetricsService metricsService;
    private final BusHandlerRegister busHandlerRegister;
    private final IdentifierGenerator identifierGenerator;

    private final CommandBus commandBus;
    private final QueryBus queryBus;
    private final EventBus eventBus;


    public XSharedModuleConfigurationAdapter() {
        this.metricsService = new MetricsServiceInMemory();
        this.busHandlerRegister = new BusHandlerRegisterInMemory();
        this.identifierGenerator = new IdentifierJavaGenerator();

        this.commandBus = new CommandBusInMemory(busHandlerRegister);
        this.queryBus = new QueryBusInMemory(busHandlerRegister);
        this.eventBus = new EventBusInMemory(busHandlerRegister);
    }

    @Bean
    public final IdentifierGenerator identifierGenerator() {
        return withIdentifierGenerator();
    }

    protected IdentifierGenerator withIdentifierGenerator(){
        return this.identifierGenerator;
    }

    @Bean
    public final BusHandlerRegister busHandlerRegister() {
        return withBusHandlerRegister();
    }

    protected BusHandlerRegister withBusHandlerRegister(){
        return this.busHandlerRegister;
    }

    @Bean
    public final MetricsService metricsService() {
        return withMetricsService();
    }

    protected MetricsService withMetricsService(){
        return this.metricsService;
    }

    @Bean
    public final CommandBus commandBus() {
        if (withMetricsModule()) {
            return new CommandBusMetric(withCommandBus(), withMetricsService());
        }

        return withCommandBus();
    }

    protected CommandBus withCommandBus() {
        return this.commandBus;
    }

    @Bean
    public final QueryBus queryBus() {
        if (withMetricsModule()) {
            return new QueryBusMetric(withQueryBus(), withMetricsService());
        }

        return withQueryBus();
    }

    protected QueryBus withQueryBus(){
        return this.queryBus;
    }

    @Bean
    public final EventBus eventBus() {
        if (withMetricsModule()) {
            return new EventBusMetric(withEventBus(), withMetricsService());
        }

        return withEventBus();
    }

    protected EventBus withEventBus(){
        return this.eventBus;
    }

    protected abstract boolean withMetricsModule();

}
