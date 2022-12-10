package com.example.websocketnetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyChattingClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String msg = "안녕서버야?";
        ByteBuf messageBuffer = Unpooled.buffer();
        messageBuffer.writeBytes(msg.getBytes());
        ctx.writeAndFlush( messageBuffer ); //메시지를 발송하고 flush처리
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("인입 확인33");
       /* String readMessage = ((ByteBuf) msg).toString(Charset.defaultCharset());
        System.out.println("Received Message [" + readMessage + "]");
        ctx.write(msg);*/
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}

