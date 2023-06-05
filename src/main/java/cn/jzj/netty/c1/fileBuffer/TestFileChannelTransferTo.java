package cn.jzj.netty.c1.fileBuffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TestFileChannelTransferTo {
    public static void main(String[] args) {
        try (FileChannel from = new FileInputStream("DownLoad.rar").getChannel()) {
            try (FileChannel to = new FileOutputStream("to.rar").getChannel()) {
                // 效率高，底层利用操作系统零拷贝进行优化， 一次最多传2g的数据
                long size = from.size();
                //left 代码剩下多少字节没有传输
                for (long left = size; left > 0 ; ) {
                    System.out.println("position:" + (size - left) + "left: " + left);
                    left -= from.transferTo((size - left), left, to);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
