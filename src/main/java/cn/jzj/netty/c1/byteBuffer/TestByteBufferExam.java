package cn.jzj.netty.c1.byteBuffer;

import java.nio.ByteBuffer;

import static cn.jzj.netty.c1.byteBuffer.ByteBufferUtil.debugAll;

public class TestByteBufferExam {
    public static void main(String[] args) {
        /*
        网络上有多条数据发送给服务端，数据之间使用  n 进行分隔但由于某种原因这些数据在接收时，被进行了重新组合，例如原始数据有3条为Hello,world\n
        I'm zhangsan\nHow are you?\n
        变成了下面的两个 byteBuffer (黏包， 半包)
        Hello,world\nI'm zhangsan\nHo
        W are you?\n
        现在要求你编写程序，将错乱的数据恢复成原始的按 n 分隔的数据
         */
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);
        source.put("W are you?\n".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {//读模式
            // 找到一条完整的消息
            if (source.get(i) == '\n'){
                int length = i + 1 - source.position();

                //把这条完整的消息存入新的targetBuffer
                ByteBuffer targetBuffer = ByteBuffer.allocate(length);
                //从source 读，向target写
                for (int j = 0; j < length; j++) {
                    targetBuffer.put(source.get());
                }
                debugAll(targetBuffer);
            }
        }
        source.compact();
    }
}
