package com.instrantes;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {
    public static void main(String[] args) {
        String password = "admin";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println(hashedPassword);
        boolean match1 = passwordEncoder.matches(password, hashedPassword);
        System.out.println(match1);
        System.out.println(match1);
        System.out.println("This is a test");
//        String str = "http://localhost:8080/upload/images/2017/09/24/045543511.jpg";
//        String aStr=str.substring(str.length()-24);
//        System.out.println(aStr);

    }

}