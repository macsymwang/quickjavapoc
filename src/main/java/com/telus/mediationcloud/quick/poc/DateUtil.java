package com.telus.mediationcloud.quick.poc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class DateUtil implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

    @Override
    public void run(String... args) throws Exception {
        String sDate1 = "20230610070000";
        Date date1 = new SimpleDateFormat("yyyyMMddHHmmss").parse(sDate1);
        System.out.println(sDate1 + "\t" + date1);
    }
}