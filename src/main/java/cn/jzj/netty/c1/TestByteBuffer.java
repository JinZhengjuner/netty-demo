package cn.jzj.netty.c1;


import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class TestByteBuffer {
    public static void main(String[] args) throws IOException {
        //FileChannel
        //1.输入输出流 2.RandomAccessFile
        FileChannel channel = new FileInputStream("data.txt").getChannel();
       while (true){
           //准备缓冲区
           ByteBuffer buffer = ByteBuffer.allocate(10);
           //从channel读取数据，向buffer写
           int len = channel.read(buffer);
            log.info("len的长度为:{}", len);
           if (len == -1) break;
           //打印buffer的内容
           buffer.flip();// 切换读写模式
           while (buffer.hasRemaining()){
               byte b = buffer.get();
               System.out.println((char) b);
           }

           buffer.flip();//把buffer切换为写模式
       }
    }
}
