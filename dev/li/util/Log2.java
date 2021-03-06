package li.util;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 日志工具类,自动适配Log4j或Console
 * 
 * @author li (limingwei@mail.com)
 * @version 0.1.6 (2012-07-05)
 */
public abstract class Log2 {
    private static final String FATAL = "FATAL", ERROR = "ERROR", WARN = "WARN", INFO = "INFO", DEBUG = "DEBUG", TRACE = "TRACE";

    /**
     * 一个缓存,可用于暂时保存一个值
     */
    private static final Map<Object, Object> LOG_MAP = new ConcurrentHashMap<Object, Object>();

    /**
     * 向LOG_MAP中设值
     */
    public static void put(Object key, Object value) {
        LOG_MAP.put(key, value);
    }

    /**
     * 从LOG_MAP中取值
     */
    public static Object get(Object key) {
        return LOG_MAP.get(key);
    }

    /**
     * Log初始化方法,自动适配Log4j或Console
     */
    public static Log2 init(final Class<?> type) {
        try {
            final Method method = Reflect.getMethod(Reflect.getType("org.apache.log4j.Logger"), "log", new Class[] { Reflect.getType("org.apache.log4j.Priority"), Object.class });
            final Map priorities = Convert.toMap(FATAL, Reflect.getStatic("org.apache.log4j.Priority", FATAL),//
                    ERROR, Reflect.getStatic("org.apache.log4j.Priority", ERROR),//
                    WARN, Reflect.getStatic("org.apache.log4j.Priority", WARN),//
                    INFO, Reflect.getStatic("org.apache.log4j.Priority", INFO),//
                    DEBUG, Reflect.getStatic("org.apache.log4j.Priority", DEBUG), //
                    TRACE, Reflect.getStatic("org.apache.log4j.Priority", DEBUG));

            return new Log2() {// 尝试初始化Log4J
                Object logger = Reflect.call("org.apache.log4j.Logger", "getLogger", new Class[] { Class.class }, new Object[] { type });

                protected void log(String priority, Object msg) {
                    Reflect.invoke(logger, method, priorities.get(priority), msg);
                }
            };
        } catch (Throwable e) {
            return new Log2() {// 返回ConsoleLog
                protected void log(String method, Object msg) {
                    ((method.equals(ERROR) || method.equals(FATAL)) ? System.err : System.out).println(method.toUpperCase() + ": " + msg);
                }
            };
        }
    }

    /**
     * 根据类名初始化Log
     */
    public static Log2 init(String className) {
        return init(Reflect.getType(className));
    }

    /**
     * 初始化Log最简单的方法,会自动获取调用者的类型
     */
    public static Log2 init() {
        return init(Thread.currentThread().getStackTrace()[2].getClassName());
    }

    /**
     * 抽象方法,由不同的Log做具体的适配
     */
    protected abstract void log(String method, Object msg);

    /**
     * 处理log信息
     */
    private static String process(Object msg, Object... args) {
        if (null == args || args.length < 1) {
            return msg + "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        char[] chars = null == msg ? new char[0] : msg.toString().toCharArray();
        int arg_index = 0;
        for (int i = 0; i < chars.length; i++) {
            stringBuffer.append((arg_index < args.length && chars[i] == '?') ? args[arg_index++] : chars[i]);
        }
        return stringBuffer.toString();
    }

    /**
     * 输出TRACE级别的日志 Level 1
     */
    public void trace(Object msg, Object... args) {
        log(TRACE, process(msg, args));
    }

    /**
     * 输出DEBUG级别的日志 Level 2
     */
    public void debug(Object msg, Object... args) {
        log(DEBUG, process(msg, args));
    }

    /**
     * 输出INFO级别的日志 Level 3
     */
    public void info(Object msg, Object... args) {
        log(INFO, process(msg, args));
    }

    /**
     * 输出WARN级别的日志 Level 4
     */
    public void warn(Object msg, Object... args) {
        log(WARN, process(msg, args));
    }

    /**
     * 输出ERROR级别的日志 Level 5
     */
    public void error(Object msg, Object... args) {
        log(ERROR, process(msg, args));
    }

    /**
     * 输出FATAL级别的日志 Level 6
     */
    public void fatal(Object msg, Object... args) {
        log(FATAL, process(msg, args));
    }
}