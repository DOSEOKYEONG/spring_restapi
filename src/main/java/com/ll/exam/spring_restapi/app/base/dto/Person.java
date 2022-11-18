package com.ll.exam.spring_restapi.app.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    private long id;
    private String name;
}
