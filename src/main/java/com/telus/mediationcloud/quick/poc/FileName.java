package com.telus.mediationcloud.quick.poc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileName implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        String fileFullName = "gs://ccs_data_tdr_process_np-c0f674/db_load/SUODS_FWHSIA_ID_20230203_1_meta_agentmetaname-CloudDBExtractionPublisher.json";
        log.info("File name:" + FilenameUtils.getName(fileFullName));

        String endTime = "65535-12-31T23:59:59.999999Z";
        Date endTimeDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").parse(endTime);
        System.out.println("endTimeDate:" + endTimeDate);

        endTime = "2023-02-16T16:14:04.000000Z";
        endTimeDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").parse(endTime);
        System.out.println("endTimeDate:" + endTimeDate);
    }

}
