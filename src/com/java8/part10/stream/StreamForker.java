package com.java8.part10.stream;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 复制流
 * 定义一个StreamForker，在一个流上执行多个操作
 * @author Mr.zxb
 * @date 2019-04-20 21:19:06
 */
public class StreamForker<T> {

    private final Stream<T> stream;
    private final Map<Object, Function<Stream<T>, ?>> forks = new HashMap<>();

    public StreamForker(Stream<T> stream) {
        this.stream = stream;
    }

    public StreamForker<T> fork(Object key, Function<Stream<T>, ?> function) {
        // 使用一个键对流上的函数进行索引
        forks.put(key, function);
        // 返回this从而保证多次流畅地调用fork方法
        return this;
    }

    public Results getResults() {
        ForkingStreamConsumer<T> consumer = build();
        try {
            stream.sequential().forEach(consumer);
        } finally {
            consumer.finish();
        }
        return consumer;
    }

    /**
     * 使用build方法创建ForkingStreamConsumer
     * @return
     */
    private ForkingStreamConsumer<T> build() {
        // 创建由队列组成的元素，每一个队列对应一个操作
        List<BlockingQueue<T>> queues = new ArrayList<>();

        // 建立用于 标识操作的键与包含操作结果的Future之间的关系
        Map<Object, Future<?>> actions = forks.entrySet().stream().reduce(new HashMap<>(), (map, e) -> {
            map.put(e.getKey(), getOperationResult(queues, e.getValue()));
            return map;
        }, (m1, m2) -> {
            m1.putAll(m2);
            return m1;
        });
        return new ForkingStreamConsumer<>(queues, actions);
    }

    /**
     * 使用getOperationResult方法返回Future
     * @param queues
     * @param function
     * @return
     */
    private Future<?> getOperationResult(List<BlockingQueue<T>> queues, Function<Stream<T>, ?> function) {
        // 创建一个队列，并将其添加到队列的列表中
        BlockingQueue<T> queue = new LinkedBlockingQueue<>();
        queues.add(queue);

        // 创建一个Spliterator，遍历队列中的元素
        Spliterator<T> spliterator = new BlockingQueueSpliterator<>(queue);
        // 创建一个流，将Spliterator作为数据源
        Stream<T> source = StreamSupport.stream(spliterator, false);
        // 创建一个Future对象，以异步方式计算在流上执行待定函数的结果
        return CompletableFuture.supplyAsync(() -> function.apply(source));
    }

}
