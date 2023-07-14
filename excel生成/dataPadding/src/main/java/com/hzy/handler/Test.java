package com.hzy.handler;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: Test
 * @Author zxwyhzy
 * @Date: 2023/7/14 17:40
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        fillTemplate();
    }

    public static List getData() {
        List<Object> data = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("key1", "啦啦啦");
        map1.put("key2", "value2");
        map1.put("key5", "value5");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("key3", "value3");
        map2.put("key4", "ooo");

        DataBook dataBook1 = new DataBook();
        dataBook1.setBooks(map1);
        dataBook1.setName("张三");
        DataBook dataBook2 = new DataBook();
        dataBook2.setBooks(map2);
        dataBook2.setName("李四");

        data.add(dataBook1);
        data.add(dataBook2);
        return data;
    }
    public static void fillTemplate() {
        //excel模板
        File file = new File("resources/map测试模板.xlsx");
        try (FileInputStream fileInputStream = new FileInputStream(file)) {

            List list = getData();
            //输出文件路径
            String fileName = "resources/map记录表.xlsx";
            ExcelWriter excelWriter = EasyExcel
                    .write(fileName)
                    .withTemplate(file)
                    .registerWriteHandler(new MyMapWriteHandler(list))
                    .build();

            WriteSheet writeSheet = EasyExcel.writerSheet(0).build();
            excelWriter.fill(list, writeSheet);
            // 关闭流
            excelWriter.finish();


        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }
}

