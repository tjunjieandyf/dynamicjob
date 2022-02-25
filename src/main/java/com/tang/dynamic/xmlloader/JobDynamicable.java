package com.tang.dynamic.xmlloader;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jiejT
 * @ClassName: JobDynamicable
 * @description: 实现job通过xml配置动态效果
 * @date 2022/2/23
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(LoadJobConetxt.class)
public @interface JobDynamicable {
}
