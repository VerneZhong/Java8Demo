package com.java8.part10.stream;

import java.util.concurrent.Future;

/**
 * 获取异步任务结果接口
 * @author Mr.zxb
 * @date 2019-04-20 22:04:19
 */
public interface Results {

    /**
     * 返回Future结果
     * @param key
     * @param <R>
     * @return
     */
    <R> R get(Object key);
}
