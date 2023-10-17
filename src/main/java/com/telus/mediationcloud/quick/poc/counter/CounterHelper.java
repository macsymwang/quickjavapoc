package com.telus.mediationcloud.quick.poc.counter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
// @Component
public class CounterHelper implements CommandLineRunner {

    @Autowired
    Counter counter;

    @Override
    public void run(String... args) throws Exception {
        log.info("Start counter ..." + counter.getCount());
        for (int i = 0; i < 20; i++) {
            log.info("counter ..." + counter.increment());
        }
    }
}
