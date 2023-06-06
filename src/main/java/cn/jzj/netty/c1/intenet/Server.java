//package cn.jzj.netty.c1.intenet;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.nio.ByteBuffer;
//import java.nio.channels.ServerSocketChannel;
//import java.nio.channels.SocketChannel;
//import java.util.ArrayList;
//import java.util.List;
//
//import static cn.jzj.netty.c1.byteBuffer.ByteBufferUtil.debugRead;
//
///**
// * 服务器
// *
// * @author jinzhengjun
// * @date 2023/06/06
// */
//@Slf4j
//public class Server {
//    public static void main(String[] args) throws IOException {
//        //使用nio来理解阻塞模式
//
//        ByteBuffer buffer = ByteBuffer.allocate(16);
//        log.info("xxx");
//
//        //1.创建了服务器
//        ServerSocketChannel ssc = ServerSocketChannel.open();
//        ssc.configureBlocking(false); //非阻塞模式  accept方法会非阻塞
//
//        //2.绑定监听端口
//        ssc.bind(new InetSocketAddress(8080));
//
//        List<SocketChannel> channels = new ArrayList<>();
//
//        while (true) {
//            //3.accept 建立与客户端连接， SocketChannel 用来与客户端之间通行
////            log.info("connecting...");
//            SocketChannel sc = ssc.accept(); //阻塞方法，线程停止运行
//            if (sc != null) {
//                log.info("connected... {}", sc);
//                sc.configureBlocking(false);
//                channels.add(sc);
//            }
//            //4. 接受客户端发送数据
//            for (SocketChannel channel : channels) {
//                //5.接收客户端发送的数据
////                log.info("before read... {}", channel);
//                int read = channel.read(buffer);//阻塞方法
//                if (read != 0 ){
//                    buffer.flip();
//                    debugRead(buffer);
//                    log.info("after read... {}", channel);
//                    buffer.clear();
//                }
//            }
//        }
//    }
//}
