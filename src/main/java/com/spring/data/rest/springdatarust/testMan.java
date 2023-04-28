package com.spring.data.rest.springdatarust;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.HashSet;
import java.util.Set;

public class testMan {

    public static void main(String[] arg){
     Set<String> set=new HashSet<>();
        for(int i=0;i<1000;i++){
         String test= RandomStringUtils.randomAlphanumeric(6);
         String finalString="SKU-"+test;
         set.add(finalString);
     }
        Set<String> setTwo = new HashSet<>(set);
       setTwo.forEach(System.out::println);
    }
}