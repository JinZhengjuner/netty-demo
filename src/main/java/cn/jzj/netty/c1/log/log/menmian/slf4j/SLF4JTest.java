package cn.jzj.netty.c1.log.log.menmian.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4JTest {

    public static final Logger log = LoggerFactory.getLogger(SLF4JTest.class);

    public static void main(String[] args) {
        log.error("error");
        log.warn("warn");
        log.info("info");
        log.debug("debug");
        log.trace("trace");
        try {
            System.out.println(1/0);
        } catch (Exception e) {
            log.error("出错:   ",e);
        }


    }
}
