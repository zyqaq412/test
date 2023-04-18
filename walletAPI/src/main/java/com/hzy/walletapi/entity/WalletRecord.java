package com.hzy.walletapi.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * (WalletRecord)表实体类
 *
 * @author makejava
 * @since 2023-04-17 15:34:52
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("wallet_record")
public class WalletRecord  {
    @TableId
    private Integer id;

    
    private Integer userId;
    // 1充值，2消费，3退款，4提现
    private Integer type;
    
    private Double amount;
    // 交易时间，默认当前时间
    private Date createdAt;



}
