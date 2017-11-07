package com.instrantes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.instrantes.Utils.ImgUtils;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileUploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
//    private String fildUpload(@RequestParam(value = "file", required = false) MultipartFile file,
    private String fildUpload(HttpServletResponse response, MultipartHttpServletRequest f,
                              HttpServletRequest request) throws Exception {
        MultipartFile file = f.getFile("file");
        //获得物理路径webapp所在路径+"/WEB-INF/upload",放在web-inf是出于安全考虑
//        String pathRoot = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/images");

        //获得物理路径webapp所在路径
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

            BufferedImage bi = ImageIO.read(new File(pathRoot + path + fileName));
            int srcWidth = bi.getWidth();
            int srcHeight = bi.getHeight();
            String pic_path = pathRootSec + path + fileName;

            Map<String, String> data = new HashMap<>();


            data.put("path", pic_path);
            data.put("width", String.valueOf(srcWidth));
            data.put("height", String.valueOf(srcHeight));
            data.put("scale", ImgUtils.imgScale(srcWidth, srcHeight, 300, 300));
            data.put("style", srcWidth >= srcHeight ? "width" : "height");/*图片等比例*/
            ObjectMapper mapper = new ObjectMapper();//用于json的转换
            String json = mapper.writeValueAsString(data);
            return json;
//            return pic_path;
        }

//        以下部分待修改
//        request.setAttribute("imagesPath", path);
//        此处可以写一个默认的图片
        return "null";
    }


    //----图片裁剪---
    @RequestMapping(value = "/imgCrop")
    @ResponseBody
    public String imgCrop(HttpServletRequest request) {
        double x = Double.valueOf(request.getParameter("x"));
        double y = Double.valueOf(request.getParameter("y"));
        double width = Double.valueOf(request.getParameter("width"));
        double height = Double.valueOf(request.getParameter("height"));
        String path = request.getParameter("path").substring(22);

        // ---img path ----
        String realPath = request.getServletContext().getRealPath("/");

        String imgPath = realPath + path;
        System.out.println(imgPath);
        String newPicPath=ImgUtils.imgCrop(imgPath, (int) x, (int) y, (int) width, (int) height);
int index = newPicPath.lastIndexOf('\\');
        String dbImgPath = newPicPath.substring(index+1);
        System.out.println(newPicPath);
        return dbImgPath;
    }
}
