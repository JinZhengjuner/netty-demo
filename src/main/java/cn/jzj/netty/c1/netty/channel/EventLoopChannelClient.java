package cn.jzj.netty.c1.netty.channel;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventLoopChannelClient {
    public static void main(String[] args) throws InterruptedException {
        //2.带有Future， Promise的类型都是和异步方法配合使用，用来处理结束
        ChannelFuture channelFuture = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override//在连接建立后被调用
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                    //异步非阻塞，main发起了调用，真正执行连接的不是main线程，而是nio线程
                }).connect("localhost", 8080);//连接需要1s钟

//        channelFuture.sync();//2.1 阻塞住当前线程，直到nio线程建立连接完毕
//        //无阻塞地向下执行获取 channel
//        Channel channel = channelFuture.channel();
//        log.info("{}", channel);
//        channel.writeAndFlush("hello, world");


        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            //在 nio 连接建立好了以后，会调用operationComplete
            public void operationComplete(ChannelFuture future) throws Exception {
                Channel channel = future.channel();
                log.info("{}", channel);
                channel.writeAndFlush("hello, world");
            }
        });//2.2 addListener(回调对象)
        //无阻塞地向下执行获取 channel


    }
}
