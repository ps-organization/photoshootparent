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


    }

}