package com.telus.mediationcloud.quick.poc.module;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeePojo {

    private String firstName;
    private String lastName;
    private Long age;
    private Date startDate;

}