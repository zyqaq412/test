package com.hzy.handler;

import com.alibaba.excel.write.handler.RowWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;
import java.util.Map;

public class MyMapWriteHandler implements RowWriteHandler {

    private List<Object> data;

    public MyMapWriteHandler(List<Object> data) {
        this.data = data;
    }

    @Override
    public void afterRowDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Integer relativeRowIndex, Boolean isHead) {

        if (!isHead) {

            int columnIndex = 1;
            // relativeRowIndex 当前行索引
            if (relativeRowIndex < data.size()) {
                DataBook dataBook = (DataBook) data.get(relativeRowIndex);
                Map<?, ?> map = dataBook.getBooks();
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    Cell cell = row.createCell(columnIndex);
                    cell.setCellValue(entry.getValue().toString());
                    columnIndex++;
                }
            }
        }


        /*        if (!isHead) {
            // 处理数据行
            Sheet sheet = writeSheetHolder.getSheet();
            int rowIndex = 0;
            int columnIndex = 1; // 数据行的列索引，从第二列开始
            for (Object obj : data) {
                if (obj instanceof Map) {
                    Map<?, ?> map = (Map<?, ?>) obj;
                   Row newRow = sheet.createRow(++rowIndex); // 创建新行
                    for (Map.Entry<?, ?> entry : map.entrySet()) {
                        Cell cell = newRow.createCell(columnIndex);
                        cell.setCellValue(entry.getValue().toString());
                        columnIndex++; // 列索引递增
                    }
                    columnIndex = 1; // 重置列索引为第一列
                }
            }
        }*/
    }
}

