package com.hzy.usercenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzy.usercenter.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-28 14:23:25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
