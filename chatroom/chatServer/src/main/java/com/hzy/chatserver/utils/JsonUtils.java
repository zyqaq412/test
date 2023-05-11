package com.hzy.chatserver.utils;

import com.alibaba.fastjson.JSON;
import com.hzy.chatserver.pojo.userMsg;

/**
 * @title: JsonUtils
 * @Author zxwyhzy
 * @Date: 2023/5/7 21:03
 * @Version 1.0
 */
public class JsonUtils {
    public static String toJson(Object object) throws Exception {
        return JSON.toJSONString(object);
    }

    public static Result<Object> parse(String string, Class<Result> resultClass) {
        return JSON.parseObject(string, resultClass);
    }
    public static userMsg parseB(String string, Class<userMsg> resultClass) {
        return JSON.parseObject(string, resultClass);
    }
}
