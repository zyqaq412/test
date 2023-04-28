package com.hzy.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzy.usercenter.domain.dto.ToEmail;
import com.hzy.usercenter.domain.entity.User;
import com.hzy.usercenter.domain.request.UserRegisterRequest;
import com.hzy.usercenter.util.Result;


/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2023-04-28 14:23:32
 */
public interface UserService extends IService<User> {

    /**
     *  用户注册
     * @param user 注册参数
     * @return 是否注册功能 或失败原因
     */
    Result userRegister(UserRegisterRequest user);

    /**
     *  判断邮箱是否注册
     * @return true|false
     */
    boolean isEmailNull(ToEmail toEmail);
}

