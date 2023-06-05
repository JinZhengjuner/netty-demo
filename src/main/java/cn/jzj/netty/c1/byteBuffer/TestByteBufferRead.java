package cn.jzj.netty.c1.byteBuffer;

import java.nio.ByteBuffer;

public class TestByteBufferRead {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        buffer.flip();

//        buffer.get(new byte[4]);
//        debugAll(buffer);
//
//        //rewind从头读取
//        buffer.rewind();
//        System.out.println(buffer.get());

        //mark & reset
        //mark 做一个标记， 记录position 位置， reset是将 position重置到mark的位置
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
        buffer.mark(); //加标记，索引2的位置
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());
        buffer.reset();
        System.out.println((char)buffer.get());
        System.out.println((char)buffer.get());




    }
}
