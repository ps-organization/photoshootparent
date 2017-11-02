package com.instrantes;



import com.alibaba.fastjson.JSONObject;


import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;



public class test {

    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    public static void main(String[] args) {
//        String password = "admin";
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
//        String hashedPassword = passwordEncoder.encode(password);
//        System.out.println(hashedPassword);
//        boolean match1 = passwordEncoder.matches(password, hashedPassword);
//        System.out.println(match1);
//        System.out.println(match1);
//        String str = "http://localhost:8080/upload/images/2017/09/24/045543511.jpg";
//        String aStr=str.substring(str.length()-24);
//        System.out.println(aStr);

        String str="{\"title\":\"Morning\",\"name\":\"Tom\"}";

//        JSONArray list= JSONObject.parseArray(str);
//        System.out.println("json:"+list.get(1));
//        Map<String,Object> map1 = (Map<String,Object>)JSONObject.parse(str);

//        System.out.println(map1));
//        List listl = new ArrayList();
//        listl = JSONObject.parseArray(str);
//        System.out.println("json2:"+listl.get(1))
//        str="dfsd";
//
//        String name="Amy";
       JSONObject array = JSONObject.parseObject(str);
        System.out.println();

//测试NIO
        List<String> lines = Arrays.asList("1st line", "2nd line");
        Map<String, Object> suggesionJson=new LinkedHashMap<>();
        suggesionJson.put("name","Tom");
        byte[] data = {1, 2, 3, 4, 5};
        try {
            //遍历集合
            Iterator iterator = suggesionJson.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String tStr=entry.getKey() + " : " + entry.getValue();
                Files.write(Paths.get("F:\\test.txt"),tStr.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.trace("trace message");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
        logger.fatal("fatal message");
        logger.info("today");
        System.out.println("Hello World!");

    }
}