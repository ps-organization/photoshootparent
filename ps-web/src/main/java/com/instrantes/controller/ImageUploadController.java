package com.instrantes.controller;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instrantes.Utils.ImgUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.format.annotation.DateTimeFormat;
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
//    定义原图跟缩略图的根路径
    private final static String PIC_PATH_ROOT = "http://localhost:8080/upload/images";
    private final static String SMALL_PIC_PATH_ROOT = "http://localhost:8080/upload/small_images";

    /**
     * 上传一个或多张图片，以下操作基本都有两次，因为是对原图和缩略图的操作
     * @param request 请求
     *@return java.lang.String
     *Date: 2017/11/21
     */
    @RequestMapping(value = "/uploadPic")
    @ResponseBody
    public String showProcess(HttpServletRequest request) throws Exception {

        //要放的原图片路径
        String uploadPath = request.getSession().getServletContext().getRealPath("upload/images");
        //要放缩略图路径
        String uploadSmallPath = request.getSession().getServletContext().getRealPath("upload/small_images");
        //2   创建一个DiskFileItemFactory工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //3   创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");

        List<FileItem> fileItems = upload.parseRequest(request); // 接收全部内容,此处该处理错误的
        Iterator<FileItem> iter = fileItems.iterator();//所有的表单项
        List<Map> listMap=new ArrayList<>();
        while (iter.hasNext()) {
            Map<String, Object> map = new HashMap<>(); //创建一个集合为数据存储做准备,此处不能放在外面，否则map每次都会改变
            FileItem item = iter.next();//循环获得每个表单项
            //获得文件名,在某些操作系统上返回路径加文件名
            String name = item.getName();
            if (name != null) {
                //设置格式，为创建文件夹以及图片名称做准备
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd/,hhmmssSSS");

                String[] folderArray = simpleDateFormat.format(new Date()).split(",");

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
                File smallDir = new File(uploadSmallPath + path);
                if (!smallDir.exists()) {
                    smallDir.mkdirs();
                }
                File savedFile = new File(dir, fileName);
//               写入修改名字的图片
                item.write(savedFile);
                //保存缩略图
                BufferedImage img= ImgUtils.resize(dir+"\\"+fileName,0.3);
                ImgUtils.writeImage(smallDir+"\\"+fileName,img,"jpg");
//                此处为读取图片,貌似获取不了webp图片长宽，暂时注释
//                BufferedImage bi = ImageIO.read(new File(uploadPath + path + fileName));
//                int srcWidth = bi.getWidth();
//                int srcHeight = bi.getHeight();
                String picPath = PIC_PATH_ROOT + path + fileName;
                String picSmallPath = SMALL_PIC_PATH_ROOT +path+ fileName;
//                存储数据
                map.put("path", picPath);
                map.put("smallPath",picSmallPath);
//                map.put("width", String.valueOf(srcWidth));
//                map.put("height", String.valueOf(srcHeight));
                listMap.add(map);
            }
        }
        return JSON.toJSONString(listMap);
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
            Map<String, String> data = new HashMap<>();
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