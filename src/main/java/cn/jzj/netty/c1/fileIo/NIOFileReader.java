package cn.jzj.netty.c1.fileIo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NIOFileReader {
    public static void main(String[] args) throws IOException {
//        read();
        Path path = Paths.get("example.txt");

        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            ByteBuffer buffer = ByteBuffer.wrap("Hello, World!".getBytes());
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void read() throws IOException {
        Path path = Paths.get("data.txt");
        FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (channel.read(buf) != -1){
            buf.flip();
            while (buf.hasRemaining()){
                String s = StandardCharsets.UTF_8.decode(buf).toString();
                System.out.println(s);
            }
            buf.clear();
        }
    }
}
