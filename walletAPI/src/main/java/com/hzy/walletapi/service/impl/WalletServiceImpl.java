package com.hzy.walletapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.walletapi.conlltro.Result;
import com.hzy.walletapi.entity.Wallet;
import com.hzy.walletapi.entity.WalletRecord;
import com.hzy.walletapi.enums.CodeEnum;
import com.hzy.walletapi.mapper.WalletMapper;
import com.hzy.walletapi.service.WalletRecordService;
import com.hzy.walletapi.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Wallet)表服务实现类
 *
 * @author makejava
 * @since 2023-04-17 15:34:13
 */
@Service
public class WalletServiceImpl extends ServiceImpl<WalletMapper, Wallet> implements WalletService {

    @Autowired
    WalletRecordService walletRecordService;
    /**
     *  查询用户余额
     * @param userId 用户id
     * @return 余额
     */
    @Override
    public Result getUserWalletBalance(int userId) {

        return Result.ResultOk(getById(userId).getBalance());
    }

    /**
     *
     * @param userId 用户id
     * @param amount 消费金额
     * @return 消费记录
     */
    @Override

    public Result userPurchase(int userId, double amount) {
        if (getById(userId).getBalance() < amount) return Result.setCodeEnum(CodeEnum.BALANCE);
        LambdaUpdateWrapper<Wallet> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Wallet::getUserId,userId);
        updateWrapper.set(Wallet::getBalance,getById(userId).getBalance()-amount);
        update(updateWrapper);

        // 添加消费记录
        WalletRecord walletRecord = new WalletRecord();
        walletRecord.setUserId(userId);
        walletRecord.setAmount(amount);
        walletRecord.setType(2);

        walletRecordService.save(walletRecord);

        return Result.ResultOk();
    }

    /**
     *
     * @param userId 用户id
     * @param amount 退款金额
     * @return 是否成功
     */
    @Override
    public Result userRefund(int userId, double amount,int recordId) {
        WalletRecord byId = walletRecordService.getById(recordId);
        if (byId.getAmount() != amount) return Result.error("退款金额有误");

        walletRecordService.removeById(recordId);
        var wallet= new Wallet();
        wallet.setUserId(userId);
        wallet.setBalance(getById(userId).getBalance()+amount);

        update(wallet,null);

        // 添加退款记录
        WalletRecord walletRecord = new WalletRecord();
        walletRecord.setUserId(userId);
        walletRecord.setAmount(amount);
        walletRecord.setType(3);
        walletRecordService.save(walletRecord);

        return Result.ResultOk();
    }


}
