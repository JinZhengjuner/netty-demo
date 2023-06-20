package cn.jzj.netty.c1.fileIo.test1;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
public class FutureTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        List<String> list = Arrays.asList("jingdong", "taobao", "pinduoduo");
        AtomicInteger atomicInteger = new AtomicInteger(0);
        long start = System.currentTimeMillis();
        List<CompletableFuture<String>> completableFutureList = list.stream().map(x ->
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(3000);
                        log.info("xx");
                    } catch (InterruptedException e) {
                        log.info(e.getMessage(), e);
                    }
                    return list.get(atomicInteger.getAndIncrement());
                })
        ).collect(Collectors.toList());

        List<String> resultList = completableFutureList.stream().map(x -> {
            String s = null;
            try {
                s = x.get(4L, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                log.info(e.getMessage(), e);
            }
            return s;
        }).collect(Collectors.toList());
        long ent = System.currentTimeMillis();
        log.info("转换结果是: {}", resultList);
        System.out.println("总共耗时：" + (ent - start));
        threadPool.shutdown();


    }

    private static void m1() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        List<String> list = Arrays.asList("jingdong", "taobao", "pinduoduo");
        AtomicInteger atomicInteger = new AtomicInteger(0);
        long start = System.currentTimeMillis();
        List<CompletableFuture<String>> completableFutureList = list.stream().map(x ->
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        try {
                            throw e;
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                    return list.get(atomicInteger.getAndIncrement());
                }, threadPool)
        ).collect(Collectors.toList());

        List<String> resultList = completableFutureList.stream().map(x -> {
            String s = "";
            try {
                 s = x.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return s;
        }).collect(Collectors.toList());
        long ent = System.currentTimeMillis();
        log.info("转换结果是: {}", resultList);
        System.out.println("总共耗时：" + (ent - start));
        threadPool.shutdown();
    }
}
