package com.wanghan.java8.feature.functional_interface;

/**
 *
 * @author wanghan
 */
@FunctionalInterface
public interface MyFunctionInterface<T, U> {

    /**
     * 转换
     * @param u
     * @return
     */
    T function(U u);

}
