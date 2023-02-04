package com.telus.mediationcloud.quick.poc;

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
    }

}
