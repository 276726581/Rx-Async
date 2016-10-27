package com.timogroup.async;

/**
 * Created by TimoRD on 2016/10/27.
 */
public interface RxFilter<T> {

    /**
     * @param o
     * @return 返回false, 过滤掉此数据
     * @throws Exception
     */
    boolean accept(T o) throws Exception;
}