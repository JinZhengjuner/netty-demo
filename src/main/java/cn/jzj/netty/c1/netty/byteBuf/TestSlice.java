package cn.jzj.netty.c1.netty.byteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;

import static io.netty.buffer.ByteBufUtil.appendPrettyHexDump;
import static io.netty.util.internal.StringUtil.NEWLINE;

@Slf4j
public class TestSlice {
    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(10);
        buf.writeBytes(new byte[]{'a','b','c','d','e','f','g','h','i','j'});
        log(buf);
        //在切片过程中，没有发生数据复制
        ByteBuf f1 = buf.slice(0, 5);//a - e
        ByteBuf f2 = buf.slice(5, 5);
        log(f1);
        log(f2);

        System.out.println("释放buf");
        buf.release();

//        f1.setByte(0, 'b');
//        log(f1);

    }

    private static void log(ByteBuf buffer) {
        int length = buffer.readableBytes();
        int rows = length / 16 + (length % 15 == 0 ? 0 : 1) + 4;
        StringBuilder buf = new StringBuilder(rows * 80 * 2)
                .append(" read index:").append(buffer.readerIndex())
                .append(" write index:").append(buffer.writerIndex())
                .append(" capacity: ").append(buffer.capacity())
                .append(NEWLINE);
        appendPrettyHexDump(buf,buffer);
        System.out.println(buf.toString());
    }
}