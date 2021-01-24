package com.hua.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffMark implements Serializable {
    private String email ;
    private int mark1 ;
    private int mark2 ;
    private int mark3 ;
}
