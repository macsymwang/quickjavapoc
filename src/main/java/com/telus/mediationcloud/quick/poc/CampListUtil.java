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
import org.apache.commons.collections4.ListUtils;
import java.util.ArrayList;
import java.util.List;

@Component
public class CampListUtil implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(CampListUtil.class);

    @Override
    public void run(String... args) throws Exception {
        log.debug("CampListUtil.run()");
        // create a ArrayList from 1 to 1000
        List<Integer> intlist = new ArrayList<Integer>();
        for (int i = 1; i <= 20; i++) {
            intlist.add(i);
        }

        int fileProcessPartitionSize = 0;
        List<List<Integer>> partitionedLists;

        if (fileProcessPartitionSize == 0) {
            // just copy intlist to partitionedLists
            partitionedLists = new ArrayList<List<Integer>>();
            partitionedLists.add(intlist);
        } else {
            partitionedLists = ListUtils.partition(intlist,
                    fileProcessPartitionSize);
        }

        // loop and print out the List<List<Integer>> partitionedLists
        int count = 0;
        for (List<Integer> list : partitionedLists) {
            count++;
            log.info("List : " + count);
            for (Integer i : list) {
                log.info("i: " + i);
            }
        }

        log.info("Partitioned List: " + partitionedLists.size());

    }
}