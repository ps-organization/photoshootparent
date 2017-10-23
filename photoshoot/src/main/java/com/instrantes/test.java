package com.instrantes;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        String str="{\"title\":\"Morning\",\"name\":\"Tom\"}";

//        JSONArray list= JSONObject.parseArray(str);
//        System.out.println("json:"+list.get(1));
        Map<String,Object> map1 = (Map<String,Object>)JSONObject.parse(str);

//        System.out.println(map1));
//        List listl = new ArrayList();
//        listl = JSONObject.parseArray(str);
//        System.out.println("json2:"+listl.get(1));

    }

}