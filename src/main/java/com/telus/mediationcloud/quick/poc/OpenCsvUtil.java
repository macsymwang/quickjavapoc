package com.telus.mediationcloud.quick.poc;

import com.telus.mediationcloud.quick.poc.module.EmployeePojo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.StringWriter;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Slf4j
@Component
public class OpenCsvUtil implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("OpenCsvUtil...");
        EmployeePojo employee = new EmployeePojo();
        BeanUtils.setProperty(employee, "firstName", "John");
        BeanUtils.setProperty(employee, "lastName", "Smith");
        BeanUtils.setProperty(employee, "startDate", new Date());
        BeanUtils.setProperty(employee, "age", 30L);

        List<EmployeePojo> employees = new ArrayList();
        employees.add(employee);

        //method 1 by opencsv
        StringWriter writer = new StringWriter();
        HeaderColumnNameMappingStrategy strategy = new HeaderColumnNameMappingStrategy();
        strategy.setType(EmployeePojo.class);

        StatefulBeanToCsv<EmployeePojo> csvWriter = new StatefulBeanToCsvBuilder<EmployeePojo>(writer)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(',')
                .withMappingStrategy(strategy)
                .build();
        try {
            csvWriter.write(employees);
            String multiLineString = writer.toString();
            String remainingLines = multiLineString.substring(multiLineString.indexOf("\n") + 1);
            log.info("csv string:" + remainingLines);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }

        // method 2 by apache CSVPrinter
        try {
            StringWriter stringWriter = new StringWriter();
            CSVPrinter csvPrinter = new CSVPrinter(stringWriter, CSVFormat.DEFAULT);
            List<Object> fieldValues = new ArrayList<>();
            for (Field field : EmployeePojo.class.getDeclaredFields()) {
                field.setAccessible(true);
                fieldValues.add(field.get(employee));
            }
            csvPrinter.printRecord(fieldValues);
            csvPrinter.close();
            log.info("csv print record string:" + stringWriter.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}