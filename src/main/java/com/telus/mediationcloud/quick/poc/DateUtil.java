package com.telus.mediationcloud.quick.poc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// @Component
public class DateUtil implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

    @Override
    public void run(String... args) throws Exception {
        String sDate1 = "20230610070000";
        Date date1 = new SimpleDateFormat("yyyyMMddHHmmss").parse(sDate1);
        System.out.println(sDate1 + "\t" + date1);

        String sDate2 = "2023-06-10T07:00:00";
        LocalDateTime date2 = LocalDateTime.parse(sDate2);
        System.out.println(sDate2 + "\t" + date2.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        String sDate3 = "2023-12-26T20:47:47Z";
        Date date3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(sDate3);
        TimeZone tz = TimeZone.getTimeZone("US/Alaska");
        System.out.println(sDate3 + " in day light saving:\t" + tz.inDaylightTime(date3));

        sDate3 = "2023-08-26T20:47:47Z";
        date3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(sDate3);
        System.out.println(sDate3 + " in day light saving:\t" + tz.inDaylightTime(date3));

        String sDate4 = "2023-09-10T09:04:35.000000-06:00";
        Date date4 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse(sDate4);
        System.out.println(sDate4 + " to date :\t" + date4);

    }
}