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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.UUID;
import java.nio.ByteBuffer;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
//import org.joda.time.DateTime;
// import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
// @Component
public class FileName implements CommandLineRunner {
    // private static final java.util.logging.Logger log =
    // LoggerFactory.getLogger(FileName.class);

    @Override
    public void run(String... args) throws Exception {

        // Format bill_cycle_cd
        String FullCycle = String.format("%1$d%2$02d%3$02d", 2023, 11, 12);
        log.info("FullCycle:" + FullCycle);

        // Get File Name from full path
        String fileFullName = "gs://ccs_data_tdr_process_np-c0f674/db_load/SUODS_FWHSIA_ID_20230203_1_meta_agentmetaname-CloudDBExtractionPublisher.json";
        log.info("File name:" + FilenameUtils.getName(fileFullName));
        loggerTest(true);

        // Hash file name to long
        UUID uuid = UUID.nameUUIDFromBytes(fileFullName.getBytes());
        ByteBuffer bb = ByteBuffer.wrap(new byte[64]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        long result = ByteBuffer.wrap(bb.array()).asLongBuffer().get();
        log.info("UUID from String " + (result & Long.MAX_VALUE));

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

        // for +655351231235959
        String dateFormatEDR = "YYYYMMddHHmmss";
        String inDate = "65535-12-31T23:59:59.999999Z";
        DateTimeFormatter inFormatter = DateTimeFormatter.ISO_DATE_TIME;
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        Date day = formatter1.parse(inDate);
        log.info("convert to dateFormatEDR:" + day);
        getMaxDate();

        processFileName();

    }

    private void loggerTest(boolean isTrue) {
        log.info("Logger test: " + isTrue);
    }

    private Date getMaxDate() {
        String dateStr = "+65535-12-31T23:59:59Z";
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime ldt = dtf.parse(dateStr, LocalDateTime::from);
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());
        log.info("convert to max date:" + output);
        return output;
    }

    private void processFileName() {
        String fileName = "inbox/SGW_SGSN_RAW_CIPDR#RawLoad_Split13_00003_20240920171314.csv.gz";

        String name = FilenameUtils.getName(fileName);
        log.info("File Name :" + name);

        String baseName = FilenameUtils.getBaseName(fileName);
        log.info("File baseName :" + baseName);

        String removeExtensionName = FilenameUtils.removeExtension(name);
        log.info("File removeExtension :" + removeExtensionName);

        removeExtensionName = StringUtils.remove(removeExtensionName, "#");
        String extension = FilenameUtils.getExtension(removeExtensionName);
        if (StringUtils.isNotBlank(extension)) {
            removeExtensionName = StringUtils.replace(FilenameUtils.removeExtension(removeExtensionName), ".", "_")
                    + '.' + extension;
        } else {
            removeExtensionName = StringUtils.replace(removeExtensionName, ".", "_");
        }

        log.info("File processed :" + removeExtensionName);

        String path = FilenameUtils.getPath(fileName);
        log.info("path Name :" + path);

        String folderName = StringUtils.substringBefore(path, "/");
        log.info("folderName Name :" + folderName);
    }

}
