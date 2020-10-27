package com.github.xustyx.xshared.metrics.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class Metric {
    private AtomicInteger success;
    private AtomicInteger warn;
    private AtomicInteger fail;

    private Metric(AtomicInteger success, AtomicInteger warn, AtomicInteger fail) {
        this.success = success;
        this.warn = warn;
        this.fail = fail;
    }

    protected static Metric create() {
        return new Metric(
                new AtomicInteger(),
                new AtomicInteger(),
                new AtomicInteger()
        );
    }

    protected void countSuccess() {
        success.incrementAndGet();
    }

    protected void countWarn() {
        warn.incrementAndGet();
    }

    protected void countFail() {
        fail.incrementAndGet();
    }

    public int success() {
        return success.get();
    }

    public int warn() {
        return warn.get();
    }

    public int fail() {
        return fail.get();
    }

    public int total() {
        return success() + warn() + fail();
    }
}
