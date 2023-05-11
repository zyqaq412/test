package com.hzy.chatserver.server;


import com.hzy.chatserver.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @title: ChatHandshakeInterceptor
 * @Author zxwyhzy
 * @Date: 2023/5/7 23:14
 * @Version 1.0
 *
 *
 * HandshakeInterceptor 是 Spring WebSocket 提供的一个拦截器接口，可以通过实现该接口，
 * 在 WebSocket 握手时进行一些自定义的操作，例如鉴权、添加头部信息等。
 *
 * 在 Spring WebSocket 中，通过添加一个或多个 HandshakeInterceptor 实例到 WebSocket 配置中的 registry 对象，
 * 来拦截 WebSocket 握手请求。具体来说，可以使用 registry.addInterceptors(...) 方法来注册拦截器。
 *
 * HandshakeInterceptor 接口包含三个方法：
 *
 * beforeHandshake: 在 WebSocket 握手之前执行。
 * afterHandshake: 在 WebSocket 握手之后执行，但是在连接建立之前。
 * afterConnectionEstablished: 在 WebSocket 连接建立之后执行。
 * 其中，beforeHandshake 方法返回一个布尔值，表示是否允许该 WebSocket 连接；
 * afterHandshake 和 afterConnectionEstablished 方法没有返回值。
 *
 * 在 beforeHandshake 方法中，可以通过 ServerHttpRequest 和 ServerHttpResponse 参数来获取请求和响应对象，
 * 并对它们进行一些自定义的操作，例如添加请求头、设置响应状态等。如果在 beforeHandshake 方法中返回 false，则拒绝该 WebSocket 连接请求。
 *
 * 在 afterHandshake 方法中，可以获取 WebSocketHandler 对象和 WebSocketSession 对象，
 * 并对它们进行一些自定义的操作，例如添加属性、设置心跳等。
 *
 * 在 afterConnectionEstablished 方法中，可以获取 WebSocketSession 对象，
 * 并对它进行一些自定义的操作，例如添加属性、设置心跳等。
 *
 * 通过实现 HandshakeInterceptor 接口，可以实现对 WebSocket 握手请求的自定义拦截和处理，从而实现一些特定的业务逻辑。
 *
 */

@Component
public class ChatHandshakeInterceptor implements HandshakeInterceptor {
    @Autowired
    private RedisCache redisCache;

    // 握手前
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
/*            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpServletRequest httpServletRequest = servletRequest.getServletRequest();
            String queryString = httpServletRequest.getQueryString();
            String[] q = queryString.split("=");*/


        }

        return true;
    }

    // 握手后
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception exception) {

    }
}
