package com.hzy.usercenter.controller;

import com.hzy.usercenter.domain.dto.ToEmail;
import com.hzy.usercenter.service.EmailService;
import com.hzy.usercenter.service.impl.EmailServiceImpl;
import com.hzy.usercenter.util.RedisCache;
import com.hzy.usercenter.util.Result;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: EmailController
 * @Author zxwyhzy
 * @Date: 2023/4/28 14:42
 * @Version 1.0
 */
@RestController
@RequestMapping("/mail")
public class EmailController {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    EmailService emailService = new EmailServiceImpl();
    @PostMapping
    public Result SendMail(@RequestBody ToEmail toEmail){
        toEmail.setSubject("注册验证码");
        // RandomStringUtils  commons-lang3引进的工具类
        String yzm = RandomStringUtils.randomNumeric(6);
        toEmail.setContent(yzm);
        redisCache.setCacheObject(toEmail.getTo(),yzm);
        // 验证码6分钟内有效
        redisCache.expire(toEmail.getTo(),60*10);
        return emailService.commonEmail(toEmail);
    }
}
