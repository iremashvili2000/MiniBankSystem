package com.example.demo.util;

import org.apache.commons.lang3.RandomStringUtils;

public class Random {

    public static String generateSmsCode(){
        return Random.make(6,true,true);
    }

    public static String generateReferal(){
        return Random.make(9,true,true);
    }

    private static String make(int length, boolean useLetters, boolean useNumbers){
        return RandomStringUtils.random(length,useLetters,useNumbers);
    }

    public static String generatelink(){ return Random.make(11,true,true);}
}
