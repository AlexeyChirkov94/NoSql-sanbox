package ru.chirkov.nosql.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("employee")
public class Employee {

    @Transient
    public static final String SEQUENCE_NAME = "employee_sequence";

    @Id
    private Integer id;

    private String name;

    private String department;

    private String salary;

}
