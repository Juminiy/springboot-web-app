package com.hua.es.utils;

public class RandomStringUtils {
    public static final String Letters = "ABCDEFGhijklmnOPQRSTuvwxyz" ;
    public static String getRadomStrings(int stringLength){
        StringBuffer stringBuffer = new StringBuffer();
        for(int i =0;i<stringLength;i++){
            stringBuffer.append(Letters.charAt((int)(Math.random()*Letters.length())));
        }
        return stringBuffer.toString() ;
    }
}
