package com.example.websocketnetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NettyChattingServer {

    private EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private EventLoopGroup workerGroup = new NioEventLoopGroup(); //숫자 미설정 시 CPU 코어수 X 2로 할당

    public void run() throws InterruptedException {
        try {
            ServerBootstrap bs = new ServerBootstrap();
            bs.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true) //상대방의 상태를 확인하는 패킷을 전송
                   // .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new NettyChattingServerHandler());
                        }
                    });
           // mainChannel = bs.bind(8888).sync().channel();
            ChannelFuture f = bs.bind(8888).sync(); //Server는 Only Port만 필요
            //.channel().closeFuture().sync(); //안해주면 Server가 꺼짐
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

}
