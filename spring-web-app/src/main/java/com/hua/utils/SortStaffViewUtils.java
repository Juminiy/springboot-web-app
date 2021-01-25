package com.hua.utils;

import com.hua.pojo.StaffView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortStaffViewUtils {
    public static List<StaffView> SortByMarkSum(List<StaffView> viewList){
            List< StaffView > res = viewList ;
            res.sort(new Comparator<StaffView>() {
            @Override
            public int compare(StaffView o1, StaffView o2) {
                int sum1 = o1.getMark1()+o1.getMark2()+o1.getMark3() ;
                int sum2 = o2.getMark1()+o2.getMark2()+o2.getMark3() ;
                return sum2 - sum1 ;
            }
        });
       return res ;
    }

    public static void main(String[] args) {

    }
}
