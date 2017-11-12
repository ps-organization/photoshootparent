package com.instrantes;

/**
 * Created by Lime on 2017/11/8
 */
public class TestStr extends Exception {
    public static void main(String[] args) {
        String str="abc.de.fg";
        String[] strArray=str.split("\\.");
        System.out.println(strArray[strArray.length-1]);
        System.out.println(str.lastIndexOf("."));
    }
}
