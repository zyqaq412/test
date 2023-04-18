package com.hzy.walletapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.walletapi.conlltro.Result;
import com.hzy.walletapi.entity.WalletRecord;


/**
 * (WalletRecord)表服务接口
 *
 * @author makejava
 * @since 2023-04-17 15:34:52
 */
public interface WalletRecordService extends IService<WalletRecord> {
    // 查询用户钱包金额变动明细的接口
    Result getUserTransactionHistory(int userId);

}

