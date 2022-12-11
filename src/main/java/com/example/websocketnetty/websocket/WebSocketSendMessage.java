package com.example.websocketnetty.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.LinkedHashSet;

@Component
public class WebSocketSendMessage {
    public LinkedHashSet<WebSocketSession> numSet = new LinkedHashSet<>();
    public void sendMessage(String payload) throws Exception {
        for(WebSocketSession sess: numSet) {
            TextMessage msg = new TextMessage(payload);
            sess.sendMessage(msg);
        }
    }

    public void setSession(LinkedHashSet<WebSocketSession> numSet){
        this.numSet = numSet;
    }
}
