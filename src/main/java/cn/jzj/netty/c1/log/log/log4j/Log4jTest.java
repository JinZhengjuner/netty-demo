package cn.jzj.netty.c1.log.log.log4j;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;

public class Log4jTest {
    public static void main(String[] args) throws InterruptedException {
        //开启log4j内置日志记录
        LogLog.setInternalDebugging(true);

        Logger logger = Logger.getLogger(Log4jTest.class);
//        for (int i = 0; i < 5; i++) {
            logger.fatal("fatal");
            logger.error("error");
            logger.warn("warn");
            logger.info("info");
            logger.debug("debug");
            logger.trace("trace");
//        }
        logger.info("-------");
        Logger logger1 = Logger.getLogger(Logger.class);
        logger1.fatal("fatal .....");
        logger1.error("error .....");
        logger1.warn("warn .....");
        logger1.info("info .....");
        logger1.debug("debug .....");
        logger1.trace("trace");


    }

    private static void Log4j入门案例() {
        //初始化配置信息，在入门案例中暂不使用配置文件
        BasicConfigurator.configure();
        //获取日志记录器
        Logger logger = Logger.getLogger(Log4jTest.class);
        //日记记录输出
        logger.info("hello log4j");

        //日志级别
        logger.fatal("fatal");
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("trace");
    }
}
