package com.hzy.handler;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @title: FillData
 * @Author zxwyhzy
 * @Date: 2023/3/31 11:11
 * @Version 1.0
 */
@Getter
@Setter
@EqualsAndHashCode
public class DataBook {
    @ExcelProperty("姓名")
    private String name;
    private Map books;

}
