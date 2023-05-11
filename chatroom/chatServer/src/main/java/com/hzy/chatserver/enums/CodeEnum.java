package com.hzy.chatserver.enums;

/**
 * @title: CodeEnum
 * @Author zxwyhzy
 * @Date: 2023/4/28 14:54
 * @Version 1.0
 */
public enum CodeEnum {
    // 成功
    SERVER_TO(0,"首次连接，推送消息"),
    SESSION_ID(1,"连接id"),
    MESSAGE(2,"消息"),
    NOT_USERNAME(401,"没有用户名");

    int code;
    String msg;

    CodeEnum(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
