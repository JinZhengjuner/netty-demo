package cn.jzj.netty.c1.netty.nianbaoBanbao;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWorldClient {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            send();
            System.out.println("finish");
        }

    }

    private static void send() {
        NioEventLoopGroup wordker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(wordker);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        //在连接 channel建立成功后就会触发active
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            ByteBuf buf = ctx.alloc().buffer(16);
                            buf.writeBytes(new byte[]{1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6, 1, 2, 3, 4});
                            ctx.writeAndFlush(buf);
                            ctx.channel().close();
                        }
                    });
                }
            });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.info("client error", e);
        } finally {
            wordker.shutdownGracefully();
        }
    }
}
