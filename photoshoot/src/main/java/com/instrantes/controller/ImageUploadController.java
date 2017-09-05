package com.instrantes.controller;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/image")
public class ImageUploadController {

    @RequestMapping(value="/showProcess")
    @ResponseBody
    public String showProcess(HttpServletRequest request, HttpServletResponse response) {
        //2   创建一个DiskFileItemFactory工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //3   创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        //监听文件上传进度,并存放在session中
        final HttpSession httpSession = request.getSession();
        upload.setProgressListener(new ProgressListener() {
            @Override
            public void update(long pBytesRead, long pContentLength, int pItems) {
                String show = pBytesRead + "/" + pContentLength + "byte";
                int rate = Math.round(new Float(pBytesRead) / new Float(pContentLength) * 100);
                httpSession.setAttribute("show", show);
                httpSession.setAttribute("rate", rate);
            }
        });
        String show = (String)request.getSession().getAttribute("show");
        int rate = (int)request.getSession().getAttribute("rate");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("show", show);
        map.put("rate", rate);
        return JSON.toJSONString(map);
    }



    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
//    private String fildUpload(@RequestParam(value = "file", required = false) MultipartFile file,
    private String fildUpload(HttpServletResponse response, MultipartHttpServletRequest f,
                              HttpServletRequest request) throws Exception {
        MultipartFile file = f.getFile("file");

        String pathRoot = request.getSession().getServletContext().getRealPath("upload/images");
        String pathRootSec = "http://localhost:8080/upload/images";
        if (!file.isEmpty()) {
//            设置格式，为创建文件夹以及图片名称做准备
            SimpleDateFormat format = new SimpleDateFormat("/yyyy/MM/dd/,hhmmssSSS");
            Date date = new Date();
//            int fileIndex = file.getOriginalFilename().lastIndexOf('.');
            String[] folderArray = format.format(date).split(",");

            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType = file.getContentType();
            //获得文件后缀名称
            String imageType = contentType.substring(contentType.indexOf("/") + 1);

            String fileName = folderArray[1] + "." + imageType;
            String path = folderArray[0];
            File dir = new File(pathRoot + path);
            if (!dir.exists()) {
//            mkdirs()可以在不存在的目录中创建文件夹
                dir.mkdirs();
            }
            file.transferTo(new File(pathRoot + path + fileName));

            //此处是用buffer读取图片以获取图片的长宽，以免后期要用到
            BufferedImage bi = ImageIO.read(new File(pathRoot + path + fileName));
            int srcWidth = bi.getWidth();
            int srcHeight = bi.getHeight();
            String pic_path = pathRootSec + path + fileName;
            Map<String, String> data = new HashMap<String, String>();
            //此处已返回一个将图片存在target中的upload的地址
            data.put("path", pic_path);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(data);
            System.out.println("this step is success!!");
            return json;
        }
        return null;
    }
}