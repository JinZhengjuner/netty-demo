package cn.jzj.netty.c1.log.log.menmian.jcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *  Log
 *  LogFactory
 *  若没有引用log4j，则默认使用jul，若引用log4j，则使用log4j，面向接口开发
 */
public class JCLTest {
    public static void main(String[] args) {
        //获取 log日志记录器对象
        Log log = LogFactory.getLog(JCLTest.class);
        //日志记录的输出
        log.info("hello jcl");
    }
}
