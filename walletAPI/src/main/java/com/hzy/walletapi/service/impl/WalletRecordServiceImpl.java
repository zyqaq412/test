package com.hzy.walletapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.walletapi.conlltro.Result;
import com.hzy.walletapi.entity.WalletRecord;
import com.hzy.walletapi.mapper.WalletRecordMapper;
import com.hzy.walletapi.service.WalletRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (WalletRecord)表服务实现类
 *
 * @author makejava
 * @since 2023-04-17 15:34:52
 */
@Service
public class WalletRecordServiceImpl extends ServiceImpl<WalletRecordMapper, WalletRecord> implements WalletRecordService {
    @Autowired
    WalletRecordMapper walletRecordMapper;
    @Override
    public Result getUserTransactionHistory(int userId) {
        LambdaQueryWrapper<WalletRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WalletRecord::getUserId,userId);
        List<WalletRecord> walletRecords = walletRecordMapper.selectList(queryWrapper);

        return Result.ResultOk(walletRecords);
    }

}
