package com.hzy.dataPadding;

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
public class DataP {
    @ExcelProperty("学生姓名")
    private String name;
    @ExcelProperty("晨检")
    private double morning;
    @ExcelProperty("午检")
    private double noon;
    @ExcelProperty("晚检")
    private double afternoon;
    @ExcelProperty("异常处置情况")
    private String z;
}
