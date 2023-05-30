package com.hzy.dataPadding;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.MapUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @title: App
 * @Author zxwyhzy
 * @Date: 2023/3/31 13:39
 * @Version 1.0
 */
public class App {
    public static void main(String[] args) {
        fillTemplate();
    }

    public static void fillTemplate() {
        //excel模板
        String templateFileName = "resources/计科12学生晨午晚检记录表模板.xlsx";
        File file = new File(templateFileName);
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            //填充到sheet的数据

            int sheetNum = 5;
            //原模板只有一个sheet，通过poi复制出需要的sheet个数的模板
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            //设置模板的第一个sheet的名称
            List<String> time = DataPadding.getTime();
            workbook.setSheetName(1, time.get(0));
            for (int i = 1; i < sheetNum; i++) {
                //复制模板，得到第i个sheet
                workbook.cloneSheet(1, time.get(i));
            }
            //写到流里
            workbook.write(bos);
            byte[] bArray = bos.toByteArray();
            InputStream is = new ByteArrayInputStream(bArray);
            //输出文件路径
            String fileName = "resources/计科12学生晨午晚检记录表.xlsx";
            ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(is).build();
            List<String> year = DataPadding.getYear();
            for (int i = 0; i < sheetNum; i++) {
                List list1 = DataPadding.getDatas();
                WriteSheet writeSheet = EasyExcel.writerSheet(time.get(i)).build();
                excelWriter.fill(list1, writeSheet);
                Map<String, String> map = MapUtils.newHashMap();
                map.put("date",year.get(i));
                excelWriter.fill(map, writeSheet);
            }
            // 关闭流
            excelWriter.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
