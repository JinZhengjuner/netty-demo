package cn.jzj.netty.c1.selector;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import static cn.jzj.netty.c1.byteBuffer.ByteBufferUtil.debugRead;

/**
 * 服务器
 *
 * @author jinzhengjun
 * @date 2023/06/06
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {

        //1.创建selector, 管理多个channel
        Selector selector = Selector.open();

        ByteBuffer buffer = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        //2. 建立selector和channel之间的联系
        SelectionKey sscKey = ssc.register(selector, 0, null);
        log.info("ssckey ... {}", sscKey);
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        //sscKey 将来事件发生后，通过它可以知道事件和哪个channel的事件  accept(有连接请求触发) connect(客户端那边连接建立) read write 四种事件
        ssc.bind(new InetSocketAddress(8080));

        while (true) {
            //3. select 方法 ,没有事件则阻塞，有事件恢复运行  在事件未处理时，会阻塞  事件发生后：要么处理，要么取消
            selector.select();
            //4.处理事件
            //4.1 拿到所有可用事件的集合
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();//处理key的时候，拿一个删除一个,否则下次会空指针
                log.info("key ... {}", key);
                // 5.区分事件类型
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.info("sc... {}", sc);
                } else if (key.isReadable()) {
                    try {
                        SocketChannel channel = (SocketChannel) key.channel(); //触发事件的channel
                        int read = channel.read(buffer);
                        if (read == -1) {
                            //客户端正常断开连接返回 -1
                            key.cancel();
                        } else {
                            buffer.flip();
                            debugRead(buffer);
                        }
                    } catch (IOException e) {
                        key.cancel(); //客户端异常断开 会触发read 需要将key取消
                        e.printStackTrace();
                    }


                }

//                key.cancel();//取消事件


            }


        }
    }
}
