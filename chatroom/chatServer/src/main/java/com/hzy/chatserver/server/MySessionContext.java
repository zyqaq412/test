package com.hzy.chatserver.server;

import javax.servlet.http.HttpSession;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @title: MySessionContext
 * @Author zxwyhzy
 * @Date: 2023/5/10 21:17
 * @Version 1.0
 */
public class MySessionContext {
    private static ConcurrentHashMap<String, HttpSession> sessionMap = new ConcurrentHashMap<>();

    public static void addSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public static void removeSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public static HttpSession getSession(String sessionID) {
        if (sessionID == null) {
            return null;
        }
        return sessionMap.get(sessionID);
    }
}


