package com.hzy.walletapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzy.walletapi.entity.Wallet;
import org.apache.ibatis.annotations.Mapper;


/**
 * (Wallet)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-17 15:34:07
 */
@Mapper
public interface WalletMapper extends BaseMapper<Wallet> {

}
