package cn.jzj.netty.c1.netty.channel;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class CloseFutureClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ChannelFuture channelFuture = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
                        ch.pipeline().addLast(new StringEncoder());
                    }
                }).connect("localhost", 8080);

        channelFuture.sync();
        Channel channel = channelFuture.channel();
        log.info("{}", channel);
        channel.writeAndFlush("hello, world");

        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true){
                String line = scanner.nextLine();
                if ("q".equalsIgnoreCase(line)){
                    channel.closeFuture();
                    break;
                }
                channel.writeAndFlush(line);
            }
        }, "input").start();

        //closeFuture对象，同步处理关闭，异步处理关闭
        ChannelFuture closeFuture = channel.closeFuture();
//        System.out.println("waiting ... close");
//        channelFuture.sync();
//        System.out.println("处理关闭之后的操作");
        closeFuture.addListener((ChannelFutureListener) future -> {
            log.info("关闭之后的操作");
            group.shutdownGracefully();
        });



    }
}
