package com.instrantes.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/contact")
public class PsContactController {

    @RequestMapping("/contact")
    public String JsonTest(HttpServletRequest request) {
        String PS_PATH = request.getSession().getServletContext().getRealPath("PsLog");
        System.out.println();
        String str = "{\"email\":\"lisi@qq.com\",\"phone\":\"13192207257\",\"gender\":\"M\"}";

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String Updateformat = "提交日期：" + dateFormat.format(now);
        //System.out.println(Updateformat);

        StringBuffer strb = new StringBuffer();
        FileWriter fw;
        PrintWriter pw;
        Map map = JSON.parseObject(str);
        Set set = map.entrySet();
        for (Object s : set) {
            System.out.println("  " + s);
        }
        try {
            System.out.println();
            fw = new FileWriter(PS_PATH + "/relation.txt", true);
            pw = new PrintWriter(fw);
            Iterator iter = set.iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                strb.append(entry.getKey() + " : " + entry.getValue());
                strb.append("\r\n");
            }
            strb.append(Updateformat);
            strb.append("\r\n");
            strb.append("__________________________________");
            strb.append("\r\n");
            pw.print(strb.toString());
            System.out.println(Updateformat);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "数据异常";
        }
        return "success";
    }
}
