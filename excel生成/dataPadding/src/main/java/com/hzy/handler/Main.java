package com.hzy.handler;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: Main
 * @Author zxwyhzy
 * @Date: 2023/7/14 16:49
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {
        List<Object> data = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");
        map1.put("key5", "value5");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("key3", "value3");
        map2.put("key4", "value4");

        Map<String, Object> map3 = new HashMap<>();
        map3.put("key6", "value6");
        map3.put("key7", "value7");
        map3.put("key8", "value8");

        data.add(map1);
        data.add(map2);
        data.add(map3);

        String fileName = "output.xlsx";
        EasyExcel.write(fileName)
                .registerWriteHandler(new MyMapWriteHandler(data))
                .sheet("Sheet1")
                .doWrite(data);
    }
}

