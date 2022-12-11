package com.example.websocketnetty;

import com.example.websocketnetty.netty.NettyChattingClient;
import com.example.websocketnetty.netty.NettyChattingServer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@RequiredArgsConstructor
public class TCPServer {
    private final NettyChattingServer nettyChattingServer;
    private final NettyChattingClient nettyChattingClient;

    @PostConstruct
    public void init() throws InterruptedException {
        nettyChattingServer.run();
        nettyChattingClient.run();
    }

    @PreDestroy
    private void destroy() throws Exception {
        nettyChattingServer.stop();
        nettyChattingClient.stop();
    }
}
