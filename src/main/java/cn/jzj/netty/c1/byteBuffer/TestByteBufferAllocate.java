package cn.jzj.netty.c1.byteBuffer;

import java.nio.ByteBuffer;

public class TestByteBufferAllocate {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        System.out.println(buffer.getClass());
        System.out.println(ByteBuffer.allocateDirect(16).getClass()); //DirectByteBuffer
        /**
         HeapByteBuffer  - java 堆内存 ， 读写效率较低，受到GC的影响
         DirectByteBuffer - 直接内存    读写效率高（少一次拷贝），不受GC影响  但分配内存的效率低一些
         */
    }
}
