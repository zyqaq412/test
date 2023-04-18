package com.hzy.walletapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzy.walletapi.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-17 15:30:55
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
