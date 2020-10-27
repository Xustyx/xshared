package com.github.xustyx.xshared.bus.infrastructure.query;

import com.github.xustyx.xshared.bus.domain.query.Query;
import com.github.xustyx.xshared.bus.domain.query.QueryBus;
import com.github.xustyx.xshared.bus.domain.query.Response;
import com.github.xustyx.xshared.exception.ApplicationException;
import com.github.xustyx.xshared.metrics.application.MetricCountCommand;
import com.github.xustyx.xshared.metrics.application.MetricsService;

public class QueryBusMetric implements QueryBus {

    private QueryBus queryBus;
    private MetricsService metricsService;

    public QueryBusMetric(QueryBus queryBus, MetricsService metricsService) {
        this.queryBus = queryBus;
        this.metricsService = metricsService;
    }

    @Override
    public <R extends Response> R ask(Query query) {
        String queryName = query.getClass().getInterfaces()[0].getSimpleName();

        try {
            R response = queryBus.ask(query);
            metricsService.countMetric(MetricCountCommand.successQuery(queryName));
            return response;
        } catch (Exception e){
            if (e instanceof ApplicationException) {
                metricsService.countMetric(MetricCountCommand.warnQuery(queryName));
            } else {
                metricsService.countMetric(MetricCountCommand.failQuery(queryName));
            }
            throw e;
        }
    }
}
