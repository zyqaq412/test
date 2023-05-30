package com.hzy.dataCreate;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @title: FillData
 * @Author zxwyhzy
 * @Date: 2023/3/31 11:11
 * @Version 1.0
 */
@Getter
@Setter
@EqualsAndHashCode
public class DataC {
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("上午")
    private double morning;
    @ExcelProperty("中午")
    private double noon;
    @ExcelProperty("下午")
    private double afternoon;
}
