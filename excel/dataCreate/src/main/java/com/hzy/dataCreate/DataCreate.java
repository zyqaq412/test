package com.hzy.dataCreate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @title: dataCreate 生成数据类
 * @Author zxwyhzy
 * @Date: 2023/3/31 12:32
 * @Version 1.0
 */
public class DataCreate {
    private static String path = "resources/1.txt";


    /**
     * 生成所有数据
     * @return 数据
     * @throws IOException io异常
     */
    public static List<DataC> getDatas() throws IOException {
        List<DataC> data = new ArrayList<>();
        List<String> studentNames = getStudentNames();
        for (String name : studentNames) {
            DataC fillData = getData(name);
            data.add(fillData);
        }
        return data;

    }

    /**
     *  生成每一个人的数据
     * @param name 姓名
     * @return 数据对象
     */
    public static DataC getData(String name){
        DataC fillData = new DataC();
        fillData.setName(name);
        fillData.setAfternoon(getTemperature());
        fillData.setNoon(getTemperature());
        fillData.setMorning(getTemperature());
        return fillData;
    }

    /**
     *  生成随机体温
     * @return double 体温
     */
    public static double getTemperature(){
        Random random = new Random();
        DecimalFormat df =new DecimalFormat("#####0.0");
        double n = random.nextDouble()*1.2+36;
        return Double.valueOf(df.format(n));
    }

    /**
     *  读取txt文件获取姓名集合
     * @return 姓名集合
     * @throws IOException io异常
     */
    public static List<String> getStudentNames() throws IOException {
        List<String> list = new ArrayList<>();
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr =  new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while((line = br.readLine()) != null){
            list.add(line);
        }
        br.close();
        isr.close();
        fis.close();
        return list;
    }

}
