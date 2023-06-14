package cn.jzj.netty.c1.netty.futurePromise;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyFutureTest {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop eventLoop = group.next();

        Future<Integer> future = eventLoop.submit(() -> {
            try {
                log.info("执行计算");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 70;
        });

//        log.info("waiting.....");
//        log.info("结果是：{}", future.get());
        future.addListener(future1 -> {
            log.info("执行结果:{}", future1.getNow());
            log.info("waiting.....");
            log.info("结果是：{}", future1.get());
        });
    }
}
