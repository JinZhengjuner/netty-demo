package cn.jzj.netty.c1.byteBuffer;


import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static cn.jzj.netty.c1.byteBuffer.ByteBufferUtil.debugAll;

/**
 *
 *
 * @author jinzhengjun
 * @date 2023/06/05
 */
public class TestByteBufferString {
    public static void main(String[] args) {
        // 字符串转为bytebuffer 三种方法
        //1. 字符串转为bytebuffer
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("hello".getBytes());
        debugAll(buffer);

        //2. Charset
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");
        debugAll(buffer2);

        //3. wrap
        ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());
        debugAll(buffer3);


        // bytebuffer转为字符串 
        String str1 = StandardCharsets.UTF_8.decode(buffer2).toString();
        System.out.println(str1);
    }
}
