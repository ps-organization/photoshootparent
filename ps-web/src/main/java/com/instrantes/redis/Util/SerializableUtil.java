package com.instrantes.redis.Util;

import java.io.ObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**该工具类作用是将redis的对象序列化，而查到的数据又反序列化成对象，暂未用
 * Created by Lime on 2017/11/3
 */
public class SerializableUtil {
    //序列化
    public static byte[] serialize(Object obj) {
        ObjectOutputStream obi = null;
        ByteArrayOutputStream bai = null;
        byte[] bs = null;
        try {
            bai = new ByteArrayOutputStream();
            obi = new ObjectOutputStream(bai);
            obi.writeObject(obj);
            bs = bai.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bai != null) {
                try {
                    bai.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bs;
    } //反序列化

    public static Object unserizlize(byte[] bs) {
        ByteArrayInputStream bis = null;
        Object obj = null;
        try {
            bis = new ByteArrayInputStream(bs);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
}
