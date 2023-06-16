package cn.jzj.netty.c1.netty.nianbaoBanbao;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWorldClient {
    public static void main(String[] args) {
        NioEventLoopGroup wordker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.group(wordker);
//            bootstrap.handler()
        }catch (Exception e){

        }finally {

        }
    }
}
