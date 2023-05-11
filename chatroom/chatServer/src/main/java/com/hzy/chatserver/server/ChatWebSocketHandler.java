package com.hzy.chatserver.server;

import com.hzy.chatserver.enums.CodeEnum;
import com.hzy.chatserver.pojo.Message;
import com.hzy.chatserver.pojo.userMsg;
import com.hzy.chatserver.utils.JsonUtils;
import com.hzy.chatserver.utils.Result;
import org.springframework.web.socket.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @title: ChatWebSocketHandler
 * @Author zxwyhzy
 * @Date: 2023/5/7 20:06
 * @Version 1.0
 *
 *
 * WebSocketHandler 是一个接口，它定义了处理 WebSocket 通信的方法，
 * 实现该接口的类可以作为 WebSocket 处理器使用。WebSocketHandler 主要定义了三个方法：
 *
 * afterConnectionEstablished: WebSocket 连接建立后调用的方法，通常用于处理连接建立后的业务逻辑。
 *
 * handleTextMessage: 处理接收到的文本消息。
 *
 * handleBinaryMessage: 处理接收到的二进制消息。
 *
 * 这些方法都会接收一个 WebSocketSession 对象作为参数，它封装了 WebSocket 的连接会话，可以通过该对象发送消息、关闭连接等操作。
 * 除此之外，WebSocketHandler 还可以定义自己的握手拦截器，用于在连接建立前对请求进行验证或者设置一些参数等操作。
 */
public class ChatWebSocketHandler implements WebSocketHandler {

    private static Map<String,WebSocketSession> SESSIONS = new ConcurrentHashMap<>();;
    private static List <Message> msgList = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("WebSocket 连接已打开：" + session.getId());
        SESSIONS.put(session.getId(),session);
        session.sendMessage(new TextMessage(JsonUtils.toJson
                (Result.set(CodeEnum.SESSION_ID,null,session.getId()))));
        session.sendMessage(new TextMessage(JsonUtils.toJson(Result.set(CodeEnum.SERVER_TO,null,msgList))));
    }
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("收到 WebSocket 消息：" + message.getPayload().toString());
        // 处理收到的消息，例如将消息保存到 Redis 数据库中
        // ...
        Result<Object> objectResult = JsonUtils.parse(message.getPayload().toString(),Result.class);
        userMsg userMsg = JsonUtils.parseB(objectResult.getData().toString(), userMsg.class);
        if (userMsg.getUsername() == null || "".equals(userMsg.getUsername())){
            session.sendMessage(new TextMessage(JsonUtils.toJson(Result.error(CodeEnum.NOT_USERNAME))));
        }
        Message msg = new Message();
        msg.setId(msgList.size());
        msg.setSender(userMsg.getUsername());
        msg.setTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        msg.setText(userMsg.getMessageInput());
        // 广播消息给所有连接的客户端
        msgList.add(msg);
        broadcast(JsonUtils.toJson(Result.set(CodeEnum.MESSAGE,session.getId(),msg)));

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.err.println("WebSocket 连接错误：" + session.getId() + ", " + exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("WebSocket 连接已关闭：" + session.getId());
        // 移除session id与websocket连接的映射关系
        String sessionId = (String) session.getAttributes().get("sessionId");
        if (sessionId != null) {
            SESSIONS.remove(sessionId);
        }
    }

    private HttpSession getSession(WebSocketSession session) {
        // 获取session id
        String sessionId = (String) session.getAttributes().get("sessionId");
        if (sessionId != null) {
            // 根据session id获取session对象
            HttpSession httpSession = MySessionContext.getSession(sessionId);
            return httpSession;
        }
        return null;
    }
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private void broadcast(String message) throws IOException {
        Set<Map.Entry<String, WebSocketSession>> entries = SESSIONS.entrySet();
        for (Map.Entry<String, WebSocketSession> sessions : entries) {
            if(sessions.getValue().isOpen()){
                sessions.getValue().sendMessage(new TextMessage(message));
            }
        }
    }

}
