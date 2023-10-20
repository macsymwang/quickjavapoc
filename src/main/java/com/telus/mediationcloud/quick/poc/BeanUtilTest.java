package com.telus.mediationcloud.quick.poc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

import com.telus.mediationcloud.quick.poc.module.EmployeePojo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BeanUtilTest implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        EmployeePojo employee = new EmployeePojo();
        BeanUtils.setProperty(employee, "firstName", "John");
        BeanUtils.setProperty(employee, "lastName", "Smith");
        BeanUtils.setProperty(employee, "startDate", new Date());
        BeanUtils.setProperty(employee, "age", 30L);
        log.info("employee:" + employee.toString());

    }

}
