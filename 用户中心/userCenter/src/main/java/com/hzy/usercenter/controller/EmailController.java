package com.hzy.usercenter.controller;

import com.hzy.usercenter.domain.dto.ToEmail;
import com.hzy.usercenter.service.EmailService;
import com.hzy.usercenter.service.impl.EmailServiceImpl;
import com.hzy.usercenter.util.Result;
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
    EmailService emailService = new EmailServiceImpl();
    @PostMapping
    public Result SendMail(@RequestBody ToEmail toEmail){

        return emailService.commonEmail(toEmail);
    }
}
