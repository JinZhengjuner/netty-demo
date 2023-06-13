package cn.jzj.netty.c1.netty.base;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class HelloServer {
    public static void main(String[] args) {
        //1.服务器端启动器，服务组装netty组件
        new ServerBootstrap()
                //2. BossEventLoop， WorkerEventLoop(selector,thread) group 组
                .group(new NioEventLoopGroup())
                //3. 选择服务器的ServerSocketChannel实现
                .channel(NioServerSocketChannel.class)//OIO BIO
                //4. boss 负责处理连接  worker（child） 负责处理读写，决定了worker(child)将来能执行哪些操作（handler）
                .childHandler(
                        //5. 代表和客户端进行数据读写的通道 ChannelInitializer负责初始化，添加别的handler
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                //6. 添加具体的handler
                                ch.pipeline().addLast(new StringDecoder());//解码handler， 将ByteBuf转成字符串
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {//自定义handler
                                    @Override //读事件
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        //打印上一步转换后的字符串
                                        System.out.println(msg);
                                    }
                                });
                            }
                        })
                .bind(8080);
    }
}
