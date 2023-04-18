package com.hzy.walletapi.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2023-04-17 15:30:57
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User  {
    // 用户id@TableId
    @TableId
    private Integer id;

    // 用户姓名
    private String name;



}
