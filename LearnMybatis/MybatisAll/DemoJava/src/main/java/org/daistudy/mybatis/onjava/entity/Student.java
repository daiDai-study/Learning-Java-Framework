package org.daistudy.mybatis.onjava.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Student {
    private Integer id;
    private String name;
    private Integer age;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;
}
