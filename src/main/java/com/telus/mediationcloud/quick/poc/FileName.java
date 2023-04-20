package com.telus.mediationcloud.quick.poc;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileName implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        // Format bill_cycle_cd
        String FullCycle = String.format("%1$d%2$02d%3$02d", 2023, 11, 12);
        log.info("FullCycle:" + FullCycle);

        // Get File Name from full path
        String fileFullName = "gs://ccs_data_tdr_process_np-c0f674/db_load/SUODS_FWHSIA_ID_20230203_1_meta_agentmetaname-CloudDBExtractionPublisher.json";
        log.info("File name:" + FilenameUtils.getName(fileFullName));
        loggerTest(true);

        // String format
        String format = "%08d";
        String seq = "105";
        log.info("Formatted String:" + String.format(format, Integer.parseInt(seq)));

        Date endTimeDate = null;
        endTimeDate = new Date();
        String strDate = new SimpleDateFormat("yyyyMMddHHmmss").format(endTimeDate);
        System.out.println(endTimeDate);
        log.info("Formatted current date:" + strDate);

        // Parsing String to date
        String endTime = "65535-12-31T23:59:59.999999Z";
        String dateParrten = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
        log.info("endTimeDate DateUtils in:" + endTime + " Out:" + DateUtils.parseDate(endTime, dateParrten));

        // Parse normal date String
        endTime = "2023-02-16T16:14:04.000000Z";
        // endTimeDate = new
        // SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").parse(endTime);
        // log.info("endTimeDate in:" + endTime + " Out:" + endTimeDate);

        // ISODatetoUTCDate
        endTime = "2023-05-05T00:00:00-04:00";
        TemporalAccessor ta = DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(endTime);
        Date dateTA = new Date(Instant.from(ta).toEpochMilli());
        log.info("ISODatetoUTCDate to Date: In:" + endTime + " Out:" + dateTA);

        Instant i = Instant.from(ta);
        ZonedDateTime zdt = ZonedDateTime.ofInstant(i, ZoneId.of("Z"));
        String dateFormat = "YYYYMMddHHmmss";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        strDate = zdt.format(dtf);
        log.info("ISODatetoUTCDate to String : In:" + endTime + " Out:" + strDate);

        Date date = DateUtils.parseDate(strDate, dateFormat);
        log.info("ISODatetoUTCDate Reverse: In:" + strDate + " Out:" + date);

        // Log format for Json to one String line
        String contents = FileUtils.readFileToString(new File("./TelusBalanceThresholdModifyEvent.json"),
                StandardCharsets.UTF_8);
        // log.info(contents.replaceAll("[\r\n]+", " "));

        String longStr = "012345";
        log.info("convert to long:" + Long.parseLong(longStr));
    }

    private void loggerTest(boolean isTrue) {
        log.info("Logger test: " + isTrue);
    }

}
