package li.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记一个需要事务包裹的方法
 * 
 * @author li (limingwei@mail.com)
 * @version 0.1.1 (2012-09-20)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Trans {
    /**
     * 事务级别,-1表示使用默认
     */
    public int value() default -1;

    /**
     * 指示是否只读
     */
    public boolean readOnly() default false;
}