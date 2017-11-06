package com.instrantes.controller;

import com.instrantes.Utils.SendEmailUtils;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/contact")
public class PsContactController {

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    @ResponseBody
    public String recordSuggestion(HttpServletRequest request, @RequestParam LinkedHashMap<String, Object> suggesionJson) throws MessagingException, GeneralSecurityException, javax.mail.MessagingException, UnsupportedEncodingException {
        /**
         *接收意见及发送人的信息，写入PsSuggestion文件relation.txt下
         *@param [request, suggesionJson]
         *@return java.lang.String
         *@date 2017/10/26
         */
        SendEmailUtils sendEmailUtils = new SendEmailUtils();
        String PS_PATH = request.getSession().getServletContext().getRealPath("PsSuggestion" + File.separator + "relation.txt");
        String email = request.getParameter("email");//获取页面传输过来的邮箱地址
        sendEmailUtils.sendEmail(email);//调用邮件发送类发送邮件
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        try {
            StringBuffer tStr = new StringBuffer();
            Iterator iterator = suggesionJson.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                tStr.append(entry.getKey()).append(":").append(entry.getValue()).append("\r\n");
            }
            //添加日期，整理格式
            tStr.append("date:").append(dateFormat.format(now))
                    .append("\r\n").append("__________________________________").append("\r\n");
            //输出到文件中,（路径，转成字节，追加内容）
            Files.write(Paths.get(PS_PATH), tStr.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }
}
