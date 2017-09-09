package com.instrantes.controller;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.apache.commons.fileupload.FileItem;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/image")
public class ImageUploadController {

    //上传一个或多张图片
    @RequestMapping(value = "/uploadPic")
    @ResponseBody
    public String showProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //2   创建一个DiskFileItemFactory工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //3   创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");

        List<FileItem> fileItems = upload.parseRequest(request); // 接收全部内容,此处该处理错误的
        Iterator<FileItem> iter = fileItems.iterator();//所有的表单项
        while (iter.hasNext()) {
            FileItem item = iter.next();//循环获得每个表单项
            //获得文件名,在某些操作系统上返回路径加文件名
            String name = item.getName();
            if (name != null) {
                //要放的图片路径
                String uploadPath = request.getSession().getServletContext().getRealPath("upload/images");
                //设置格式，为创建文件夹以及图片名称做准备
                SimpleDateFormat format = new SimpleDateFormat("/yyyy/MM/dd/,hhmmssSSS");
                Date date = new Date();
                String[] folderArray = format.format(date).split(",");

                File fullFile = new File(name);
                //获得文件后缀名称,因此以下再获取一次name确保正确
                String imageType = fullFile.getName().substring(fullFile.getName().indexOf(".") + 1);
                String fileName = folderArray[1] + "." + imageType;
                //mkdirs()可以在不存在的目录中创建文件夹
                String path = folderArray[0];
                File dir = new File(uploadPath + path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File savedFile = new File(dir, fileName);
                item.write(savedFile);
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
//            map.put("show", show);
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