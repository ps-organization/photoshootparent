package com.instrantes.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImgUtils {
    /**
     * 截取图片
     *
     * @param srcImageFilePath 原图片地址
     * @param x                截取时的x坐标
     * @param y                截取时的y坐标
     * @param desWidth         截取的宽度
     * @param desHeight        截取的高度
     */
    public static String imgCrop(String srcImageFilePath, int x, int y,
                                 int desWidth, int desHeight) {
        try {
            Image img;
            ImageFilter cropFilter;
            BufferedImage bi = ImageIO.read(Files.newInputStream(Paths.get(srcImageFilePath)));
            int srcWidth = bi.getWidth();
            int srcHeight = bi.getHeight();
            if (srcWidth >= desWidth && srcHeight >= desHeight) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight,
                        Image.SCALE_DEFAULT);
                cropFilter = new CropImageFilter(x, y, desWidth, desHeight);
                img = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(desWidth, desHeight,
                        BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null);
                g.dispose();//释放资源
                // 输出文件
                System.out.println(srcImageFilePath);
                int index = srcImageFilePath.indexOf('.');
                String newPic = srcImageFilePath.substring(0, index - 1) + "0" + srcImageFilePath.substring(index);
                ImageIO.write(tag, "JPEG", new File(newPic));
                System.out.println(newPic);
                return newPic;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "图片异常:ImgUtils!";
    }

    /**
     * 分析图片的尺寸比例
     * 2015年10月19日 下午2:22:34
     *
     * @param width
     * @param height
     * @param divWidth
     * @param divHeight
     * @return
     */
    public static String imgScale(int width, int height, int divWidth,int divHeight) {

        Double scale = 1.0;
        if (divWidth >= width && divHeight >= height) {
            scale = 1.0;
        }

        if (width >= height && width >= divWidth) {
            scale = (double) width / divWidth;
        }

        if (height > width && height >= divHeight) {
            scale = (double) height / divHeight;
        }

        return String.valueOf(scale);
    }


    /**
     * 将图片压缩成缩略图
     *
     * @param fileName        图片名字
     * @param compressPercent 压缩大小
     * @return java.awt.image.BufferedImage
     * Date: 2017/11/20
     */
    public static BufferedImage resize(String fileName, double compressPercent) {
        BufferedImage originalImg = null;//原图
        BufferedImage compressImg = null;//压缩后图
        int width, height;
        try {
            originalImg = ImageIO.read(Files.newInputStream(Paths.get(fileName)));
            width = (int) (originalImg.getWidth() * compressPercent);
            height = (int) (originalImg.getHeight() * compressPercent);
            compressImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);//压缩后图
            compressImg.getGraphics().drawImage(originalImg, 0, 0, width, height, null); // 绘制缩小后的图
        } catch (IOException e) {
            e.printStackTrace();
        }
        return compressImg;
    }

    /**
     *写入图片功能
     * @param file 图片路径
     * @param img 图片缓存
     * @param formatName 图片格式，貌似暂时jpg,png,gif
     *@return void
     *Date: 2017/11/20
     */
    public static void writeImage(String file, BufferedImage img,String formatName) {
        try {
            //此处或许应该用缓存输出流做优化，但暂未解决
            ImageIO.write(img, formatName, Files.newOutputStream(Paths.get(file)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
