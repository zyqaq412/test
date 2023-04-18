package com.hzy.walletapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzy.walletapi.entity.WalletRecord;
import org.apache.ibatis.annotations.Mapper;


/**
 * (WalletRecord)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-17 15:34:51
 */
@Mapper
public interface WalletRecordMapper extends BaseMapper<WalletRecord> {

}
