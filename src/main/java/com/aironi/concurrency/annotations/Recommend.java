package com.aironi.concurrency.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 仅用来标记推荐使用的类写法
 * @author emora
 *
 */
@Target(ElementType.TYPE) 
@Retention(RetentionPolicy.SOURCE)
public @interface Recommend {

}
