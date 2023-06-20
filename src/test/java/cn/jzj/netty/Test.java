package cn.jzj.netty;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * 原理解析：
 * 1. 初始化LogManager
 *  1.1 LogManager加载logging.properties配置
 *  1.2 添加Logger到LogManager
 * 2. 从单例LogManager过去Logger
 * 3.设置级别Level，并指定日志记录LogRecord
 * 4.Filter设置了日志级别之外更细粒度的控制
 * 5.Handler是用来处理日志输出位置
 * 6.Formatter是用来格式化LogRecord的
 */
public class Test {
    public static void main(String[] args) throws IOException {
//        Logger logger = Logger.getLogger("cn.jzj.netty.Test");

        //读取配置文件，通过类加载器
        InputStream ips = Test.class.getClassLoader().getResourceAsStream("logging.properties");
        //创建LogManager
        LogManager logManager = LogManager.getLogManager();
        //通过LogManager加载配置文件
        logManager.readConfiguration(ips);

        //创建日志记录器
        Logger logger = Logger.getLogger("cn.jzj.netty.Test");

        logger.severe("servere");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    private static void Logger父子关系() {
        //Logger对象的父子关系
        Logger logger1 = Logger.getLogger("cn.jzj");
        Logger logger2 = Logger.getLogger("cn.jzj.netty");
        System.out.println(logger2.getParent() == logger1);//true
    }

    private static void 自定义日志级别() throws IOException {
        Logger logger = Logger.getLogger("cn.jzj.netty.Test");
        //自定义日志级别
        logger.setUseParentHandlers(false);//关闭默认日志级别配置
        //创建ConsolHanler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        //创建简单格式转换对象
        SimpleFormatter simpleFormat = new SimpleFormatter();
        //进行关联
        consoleHandler.setFormatter(simpleFormat);
        logger.addHandler(consoleHandler);
        //配置具体日志级别
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        FileHandler fileHandler = new FileHandler("jul.log");
        //进行关联
        fileHandler.setFormatter(simpleFormat);
        logger.addHandler(fileHandler);
        logger.severe("servere");
        logger.warning("warning");
        logger.info("info");//JUL默认日志级别
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    private static void testLoggerLevel() {
        Logger logger = Logger.getLogger("cn.jzj.netty.Test");
        logger.severe("servere");
        logger.warning("warning");
        logger.info("info");//JUL默认日志级别
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    private static void testQuick() {
        //1.获取日志记录器对象
        Logger logger = Logger.getLogger("cn.jzj.netty.Test");
        //2.日志记录输出
        logger.info("hello JUL");

        //通用方法进行日志记录
        logger.log(Level.INFO, "info msg");

        //通过占位符输出
        String name = "itcast";
        Integer age = 13;
        logger.log(Level.INFO, "用户信息:{0},{1}",new Object[]{name, age});//用户信息:itcast,13
        logger.log(Level.INFO, "用户信息:{},{}",new Object[]{name, age});//用户信息:{},{}
    }


}
