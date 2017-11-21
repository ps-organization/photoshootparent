package com.instrantes;

import com.instrantes.Utils.ImgUtils;

import java.awt.image.BufferedImage;

/**
 * Created by Lime on 2017/11/20
 */
public class TestImgUtil {
    public static void main(String[] args) {
        String imgPath="C:\\001.png";
        BufferedImage img=ImgUtils.resize(imgPath,0.3);
        ImgUtils.writeImage("F:\\TTTT\\1.jpg",img,"jpg");

    }
}
