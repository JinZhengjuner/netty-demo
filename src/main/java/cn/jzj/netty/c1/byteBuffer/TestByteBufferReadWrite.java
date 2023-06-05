package cn.jzj.netty.c1.byteBuffer;

import java.nio.ByteBuffer;

public class TestByteBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61); // 'a'
        ByteBufferUtil.debugAll(buffer);
        buffer.put(new byte[]{0x62, 0x63, 0x64});// b c d
        ByteBufferUtil.debugAll(buffer);
//        byte b = buffer.get();
//        System.out.println(b);
        buffer.flip();
        System.out.println(buffer.get());
        ByteBufferUtil.debugAll(buffer);

        buffer.compact();// 未读的全部往前移动
        ByteBufferUtil.debugAll(buffer);
        buffer.put(new byte[]{0x65, 0x66});
        ByteBufferUtil.debugAll(buffer);
    }
}
