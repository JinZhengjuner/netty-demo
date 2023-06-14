package cn.jzj.netty.c1.netty.futurePromise;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

@Slf4j
public class PromisTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EventLoop eventLoop = new NioEventLoopGroup().next();
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);

        new Thread(() -> {
            try {
                //任意一个线程执行计算，向promise填充结果
                log.info("计算开始...");
                int i = 1/0;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                promise.setFailure(e);
                e.printStackTrace();
            }
            promise.setSuccess(80);
        }).start();

        //4.接受结果的线程
        log.info("等待结果...");
        log.info("结果是：{}", promise.get());


    }
}
