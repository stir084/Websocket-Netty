package com.example.websocketnetty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

@Component
@RequiredArgsConstructor
public class NettyChattingClient {
    private Channel channel;
    private EventLoopGroup workerGroup = new NioEventLoopGroup();
    private final NettyChattingClientHandler nettyChattingClientHandler;

    public void run() {
        SocketAddress address = new InetSocketAddress("127.0.0.1", 8888);

        try {
            Bootstrap bs = new Bootstrap();
            bs.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(nettyChattingClientHandler);
                        }
                    });

            channel = bs.connect(address).sync().channel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        workerGroup.shutdownGracefully();
    }

    public void sendToServer(String msg){
        ByteBuf messageBuffer = Unpooled.buffer();
        messageBuffer.writeBytes(msg.getBytes());
        channel.writeAndFlush(messageBuffer);
    }
}
