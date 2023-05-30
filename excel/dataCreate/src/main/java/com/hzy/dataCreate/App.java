package com.hzy.dataCreate;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       // simpleWrite();
        try {
            repeatedWrite();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  重复多次写入
     * @throws IOException io异常
     */
    public static void repeatedWrite() throws IOException {
        // 方法2: 如果写到不同的sheet 同一个对象
        String filePath ="resources/test1.xlsx";//生成文件
        // 这里 指定文件
        try (ExcelWriter excelWriter = EasyExcel.write(filePath , DataC.class).build()) {
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
            for (int i = 1; i < 6; i++) {
                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "星期" + i).build();
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<DataC> data = DataCreate.getDatas();
                excelWriter.write(data, writeSheet);
            }
        }

    }
}
