package com.hua.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    private Integer id ;
    private String lastName ;
    private String email ;
    private Integer gender ;// 0 女 1 男
    private Date birth ;
    private int department ;
}
