package com.hua.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffView implements Serializable {
    private String email ;
    private String firstName ;
    private String lastName ;
    private String workName;
    private int adjust ;
    private String volunteer1 ;
    private String volunteer2 ;
    private int mark1 ;
    private int mark2 ;
    private int mark3 ;
}
