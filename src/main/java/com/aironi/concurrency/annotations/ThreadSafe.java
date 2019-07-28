package com.aironi.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 仅用来标记线程安全的类
 * @author emora
 *
 */
@Target(ElementType.TYPE) // 该注解要添加到类上
@Retention(RetentionPolicy.SOURCE) // 注解范围, SOURCE: 编译器将忽略; RUNTIME: 该注解将编译到class文件中,并保留在JVM运行时
public @interface ThreadSafe {

	String value() default "";
}
