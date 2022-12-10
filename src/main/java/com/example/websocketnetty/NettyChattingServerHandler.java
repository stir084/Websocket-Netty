package com.example.websocketnetty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class NettyChattingServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("인입 확인");
        System.out.println(msg);
        String readMessage = ((ByteBuf) msg).toString(Charset.defaultCharset());
        System.out.println("Received Message [" + readMessage + "]");
        ctx.write(msg); //채널로 발행..
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}

