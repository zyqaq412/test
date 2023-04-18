package com.hzy.walletapi.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Wallet)表实体类
 *
 * @author makejava
 * @since 2023-04-17 15:34:09
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("wallet")
public class Wallet  {
    // 用户id@TableId
    @TableId
    private Integer userId;

    // 余额，保留两位小数
    private Double balance;



}
