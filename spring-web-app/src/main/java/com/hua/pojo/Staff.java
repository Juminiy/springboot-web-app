package com.hua.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff implements Serializable {
    private String firstName ;
    private String lastName ;
    private String nickName ;
    private String email ;
    private String workName ;
    private int grade ;
    private int sex ;

    private int adjust ;
    private String volunteer1 ;
    private String volunteer2 ;
}
