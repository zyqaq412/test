package com.hzy.usercenter.service;

import com.hzy.usercenter.util.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @title: FileService
 * @Author zxwyhzy
 * @Date: 2023/5/15 23:12
 * @Version 1.0
 */
public interface FileService {

    Result uploadImg(MultipartFile img);
}

