package com.telus.mediationcloud.quick.poc.counter;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Counter {
    private AtomicInteger count = new AtomicInteger(0);
    private static int limit = 10;
    private static int init = 123;

    public int increment() {
        if (count.get() == 0) {
            count.set(init);
            log.info("Count: set init value");
        }

        if (count.get() - init >= limit) {
            count.set(init);
            log.info("Count: Reache limit reset init value");
        }
        return count.incrementAndGet();
    }

    public void decrement() {
        int currentCount;
        do {
            currentCount = count.get();
        } while (currentCount > 0 && !count.compareAndSet(currentCount, currentCount - 1));
    }

    public int setCount(int delta) {
        return count.addAndGet(delta);
    }

    public int getCount() {
        return count.get();
    }
}