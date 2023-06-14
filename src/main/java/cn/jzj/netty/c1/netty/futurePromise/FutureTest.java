package cn.jzj.netty.c1.netty.futurePromise;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(() -> {
            log.info("starter...");
            Thread.sleep(1000);
            return 50;
        });

        log.info("等待结果");
        log.info(String.valueOf(future.get()));
    }
}
