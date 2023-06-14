package cn.jzj.netty.c1.path.path;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

public class TestWalkFileTree {
    public static void main(String[] args) throws IOException {
       String source = "E:\\Temp\\TxGameDownload";
       String target = "E:\\Temp\\TxGameDownloadaaa";
       Files.walk(Paths.get(source)).forEach(path -> {
           String targetName = path.toString().replace(source, target);
           if ( Files.isDirectory(path)){
               //一个目录
               try {
                   Files.createDirectory(Paths.get(targetName));
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }else if (Files.isRegularFile(path)){
               //是个普通文件
               try {
                   Files.copy(path, Paths.get(targetName));
               } catch (IOException e) {
                   e.printStackTrace();
               }

           }
       });

    }

    private static void deleteDirect() throws IOException {
        Path path = Paths.get("E:\\FFOutput");
        // 文件目录数目
        final AtomicInteger dirCount = new AtomicInteger();
        // 文件数目
        final AtomicInteger fileCount = new AtomicInteger();
        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return super.postVisitDirectory(dir, exc);
            }
        });
        // 打印数目
        System.out.println("文件目录数:"+dirCount.get());
        System.out.println("文件数:"+fileCount.get());
    }

    private static void m1() throws IOException {
        final AtomicInteger dirCount = new AtomicInteger();
        final AtomicInteger fileCount = new AtomicInteger();
        Files.walkFileTree(Paths.get("E:\\FFOutput"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("===>" + dir);
                dirCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path targetPath = Paths.get(file.getFileName()+".csv");
                Files.move(file, targetPath);
                System.out.println("移动成功：old: " + file.getFileName().toString() + "  new: " + targetPath);
                fileCount.incrementAndGet();
                return super.visitFile(file, attrs);
            }
        });
    }
}
