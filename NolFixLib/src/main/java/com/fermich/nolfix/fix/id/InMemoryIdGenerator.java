package com.fermich.nolfix.fix.id;

import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryIdGenerator implements IdGenerator {

    private AtomicInteger curId = new AtomicInteger(0);

    @Override
    public int nextId() {
        return curId.incrementAndGet();
    }

}
