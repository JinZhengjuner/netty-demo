package cn.jzj.netty.c1.netty.base;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TestEventLoop {
    public static void main(String[] args) {
        //1.创建事件循环组
        EventLoopGroup group = new NioEventLoopGroup(2);//io事件，普通任务，定时任务
//        EventLoopGroup group1 = new DefaultEventLoop();//普通任务，定时任务
        //2.获取下一个事件循环对象
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());
        //3.执行普通任务
//        group.next().submit(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info("ok");
//        });

        //4.执行定时任务
        group.next().scheduleAtFixedRate(() -> {
            log.info("ok");
        },0L,1L, TimeUnit.SECONDS);

        log.info("main");
    }
}
