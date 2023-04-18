package com.hzy.walletapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.walletapi.conlltro.Result;
import com.hzy.walletapi.entity.Wallet;


/**
 * (Wallet)表服务接口
 *
 * @author makejava
 * @since 2023-04-17 15:34:10
 */
public interface WalletService extends IService<Wallet> {
    // 查询用户钱包余额
    Result getUserWalletBalance(int userId);

    // 用户消费接口   100元   type 2
    Result userPurchase(int userId, double amount);

    // 用户退款接口  20元 type 3
    Result userRefund(int userId,double amount,int recordId);

}

