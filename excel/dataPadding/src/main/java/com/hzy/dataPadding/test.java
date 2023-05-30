package com.hzy.dataPadding;

import java.io.UnsupportedEncodingException;

/**
 * @title: test
 * @Author zxwyhzy
 * @Date: 2023/3/31 19:19
 * @Version 1.0
 */
public class test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "我来了";
        String utf8 = new String(s.getBytes("GBK"));

        System.out.println(s);
        System.out.println(utf8);

        for (byte b: s.getBytes()) {
            System.out.print(b+" ");
        }
        System.out.println();
        for (byte b: utf8.getBytes("GBK")) {
            System.out.print(b+" ");
        }
    }

}
