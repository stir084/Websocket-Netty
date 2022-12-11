package com.example.websocketnetty.websocket;

import com.example.websocketnetty.netty.NettyChattingClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.LinkedHashSet;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomWebSocketHandler extends TextWebSocketHandler {

    private static LinkedHashSet<WebSocketSession> numSet = new LinkedHashSet<>();

    private final WebSocketSendMessage webSocketSendMessage;
    private final NettyChattingClient nettyChattingClient;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payload = message.getPayload();

        boolean isSessionAlive = false;

        for(WebSocketSession sess: numSet) {
            if(sess.getId().equals(session.getId())){
                isSessionAlive = true;
            }
        }
        if(isSessionAlive){
            nettyChattingClient.sendToServer(payload);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        numSet.add(session);
        webSocketSendMessage.setSession(numSet);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        numSet.remove(session);
    }
}