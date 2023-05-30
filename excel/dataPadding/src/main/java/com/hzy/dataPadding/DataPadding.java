package com.hzy.dataPadding;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @title: DataPadding
 * @Author zxwyhzy
 * @Date: 2023/3/31 13:27
 * @Version 1.0
 */
public class DataPadding {

    private static String path = "resources/1.txt";


    /**
     * 生成所有行的数据
     * @return 数据
     * @throws IOException io异常
     */
    public static List<DataP> getDatas() throws IOException {
        List<DataP> data = new ArrayList<>();
        List<String> studentNames = getStudentNames();
        for (String name : studentNames) {
            DataP fillData = getData(name);
            data.add(fillData);
        }
        return data;

    }

    /**
     *  生成一行的数据
     * @param name 姓名
     * @return 数据对象
     */
    public static DataP getData(String name){
        DataP fillData = new DataP();
        fillData.setName(name);
        fillData.setAfternoon(getTemperature());
        fillData.setNoon(getTemperature());
        fillData.setMorning(getTemperature());
        fillData.setZ("无");
        return fillData;
    }

    /**
     *  生成随机体温
     * @return double 体温  区间36.3到36.9
     */
    public static double getTemperature(){
        Random random = new Random();
        DecimalFormat df =new DecimalFormat("#####0.0");
        double n = random.nextDouble()*1.0+36;
        n = Double.valueOf(df.format(n));
        if (n<36.3) n = 36.3;
        if (n == 37) n = 36.8;
        return n;
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
           line = new String(line.getBytes(),"utf-8");
            // System.out.println(line);
            list.add(line);
        }
        br.close();
        isr.close();
        fis.close();
        return list;
    }

    /**
     *  根据日期获取 sheet名
     * @return sheet名  例：3.14
     */
    public static List<String> getTime(){
        List<String> times = new ArrayList<>();
        SimpleDateFormat sdf= new SimpleDateFormat("MM.dd");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        String time= sdf.format(calendar.getTime());
        times.add(time);
        for (int i = 0; i < 4; i++) {
            calendar.add(calendar.DATE,1);
            time= sdf.format(calendar.getTime());
            times.add(time);
        }
        return times;
    }

    /**
     *  获取填表日期
     * @return 年月日 例：2023/3/14
     */
    public static List<String> getYear(){
        List<String> times = new ArrayList<>();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        String time= sdf.format(calendar.getTime());
        times.add(time);
        for (int i = 0; i < 4; i++) {
            calendar.add(calendar.DATE,1);
            time= sdf.format(calendar.getTime());
            times.add(time);
        }
        return times;
    }
}
