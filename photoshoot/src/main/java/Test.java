import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

    public static void main(String[] args) {

        //JsonOne();
        //测试将Json数据存入Map之后存入本地
        JsonTest();
        //测试获取当前系统日期
        //date();

    }


    public static String JsonTest(){
        String str = "{\"email\":\"lisi@qq.com\",\"phone\":\"13192207257\",\"gender\":\"M\"}";

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String Updateformat = "提交日期：" + dateFormat.format( now );
        //System.out.println(Updateformat);

        StringBuffer strb = new StringBuffer();
        FileWriter fw;
        PrintWriter pw = null;
        Map map = JSON.parseObject(str);
        Set set = map.entrySet();
        for (Object s: set) {
            System.out.println("  " + s);
        }
        try{
           fw = new FileWriter("src/main/resources/PsLog/relation.txt", true);
           pw = new PrintWriter(fw);
            Iterator iter = set.iterator();
            while(iter.hasNext()) {
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
        } catch (IOException e) {
            e.printStackTrace();
            return  "数据异常";
        }finally {
            pw.close();
        }
        return "success";
        }

    public static void date(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String deteformat = "提交日期：" + dateFormat.format( now );
        System.out.println(deteformat);
    }

    public static void JsonOne(){
        String str = "{\"0\":\"zhangsan\",\"1\":\"lisi\",\"2\":\"wangwu\",\"3\":\"maliu\"}";
        //第一种方式
        Map maps = (Map) JSON.parse(str);
        System.out.println("这个是用JSON类来解析JSON字符串!!!");
        for (Object map : maps.entrySet()){
            System.out.println(((Map.Entry)map).getKey()+"     " + ((Map.Entry)map).getValue());
        }
        //第二种方式
        Map mapTypes = JSON.parseObject(str);
        System.out.println("这个是用JSON类的parseObject来解析JSON字符串!!!");
        for (Object obj : mapTypes.keySet()){
            System.out.println("key为："+obj+"值为："+mapTypes.get(obj));
        }
        //第三种方式
        Map mapType = JSON.parseObject(str,Map.class);
        System.out.println("这个是用JSON类,指定解析类型，来解析JSON字符串!!!");
        for (Object obj : mapType.keySet()){
            System.out.println("key为："+obj+"值为："+mapType.get(obj));
        }
        //第四种方式
        /**
         * JSONObject是Map接口的一个实现类
         */
        Map json = (Map) JSONObject.parse(str);
        System.out.println("这个是用JSONObject类的parse方法来解析JSON字符串!!!");
        for (Object map : json.entrySet()){
            System.out.println(((Map.Entry)map).getKey()+"  "+((Map.Entry)map).getValue());
        }
        //第五种方式
        /**
         * JSONObject是Map接口的一个实现类
         */
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println("这个是用JSONObject的parseObject方法来解析JSON字符串!!!");
        for (Object map : json.entrySet()){
            System.out.println(((Map.Entry)map).getKey()+"  "+((Map.Entry)map).getValue());
        }
        //第六种方式
        /**
         * JSONObject是Map接口的一个实现类
         */
        Map mapObj = JSONObject.parseObject(str,Map.class);
        System.out.println("这个是用JSONObject的parseObject方法并执行返回类型来解析JSON字符串!!!");
        for (Object map: json.entrySet()){
            System.out.println(((Map.Entry)map).getKey()+"  "+((Map.Entry)map).getValue());
        }
        String strArr = "{{\"0\":\"zhangsan\",\"1\":\"lisi\",\"2\":\"wangwu\",\"3\":\"maliu\"}," +
                "{\"00\":\"zhangsan\",\"11\":\"lisi\",\"22\":\"wangwu\",\"33\":\"maliu\"}}";
        // JSONArray.parse()
        System.out.println(json);
        System.out.println();
    }
}
