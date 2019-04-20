package com.java8.part10.stream;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.function.Consumer;

/**
 * 实现ForkingStreamConsumer类，为其添加处理多个队列的流元素
 * @author Mr.zxb
 * @date 2019-04-20 21:31:20
 */
public class ForkingStreamConsumer<T> implements Consumer<T>, Results {

    public static final Object END_OF_STREAM = new Object();

    private final List<BlockingQueue<T>> queues;
    private final Map<Object, Future<?>> actions;

    public ForkingStreamConsumer(List<BlockingQueue<T>> queues, Map<Object, Future<?>> actions) {
        this.queues = queues;
        this.actions = actions;
    }


    @Override
    public <R> R get(Object key) {
        try {
            // 等待Future任务完成相关的计算，返回由特定键标识的处理结果
            return (R) actions.get(key).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void accept(T t) {
        // 将流中遍历的元素添加到所有的队列中
        queues.forEach(q -> q.add(t));
    }

    public void finish() {
        // 将最后一个元素添加到队列中，表明该流已经结束
        accept((T) END_OF_STREAM);
    }
}
