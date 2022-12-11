package com.example.websocketnetty.netty;

import com.example.websocketnetty.websocket.WebSocketSendMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Slf4j
@RequiredArgsConstructor
@Component
public class NettyChattingClientHandler extends ChannelInboundHandlerAdapter {

    private final WebSocketSendMessage webSocketSendMessage;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("Netty Client Connected");
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String readMessage = ((ByteBuf) msg).toString(Charset.defaultCharset());
        log.info("[Receive From Server] ---- {}", readMessage);
        webSocketSendMessage.sendMessage(readMessage);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}

