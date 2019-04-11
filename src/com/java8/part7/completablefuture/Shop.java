package com.java8.part7.completablefuture;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 在线商店
 *
 * @author Mr.zxb
 * @date 2019-04-08 14:41
 */
public class Shop {

    private final String name;

    /**
     * 随机数
     */
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    /**
     * 执行异步任务线程池
     * 优化线程池，定制执行器
     * 设置100个线程的线程池
     * 线程数 = 处理器核的数目 *  期望的CPU利用率 * （ 1 + w/c）
     * w/c是等待时间与计算时间的比率
     */
    private static final ExecutorService executor = Executors.newFixedThreadPool(100, r -> {
        Thread thread = new Thread(r);
        // 使用守护线程，这种方式不会阻止程序关停
        thread.setDaemon(true);
        return thread;
    });

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * 获取价格--同步API
     *
     * @param product
     * @return
     */
    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    public static double getPriceAsDouble(String product) {
        return calculatePrice(product);
    }

    /**
     * 将同步方法-转换为异步方法
     * {@link CompletableFuture#complete(Object)}
     *
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync(String product) {
//        // 创建CompletableFuture对象，它会包含计算的结果
//        CompletableFuture<Double> completablefuture = new CompletableFuture<>();
//
//        // 在另一个线程中以异步方式执行计算
//        // 需要长时间计算的任务结束并得出结果时，设置Future的返回值
//        executor.execute(() -> completablefuture.complete(calculatePrice(product)));
//
//        // 无需等待返回结果，直接返回future对象
//        return completablefuture;

        // 等价于下面

        // 使用CompletableFuture的工厂方法，使用默认ForkJoinPool线程池执行或是自定义线程池
        return CompletableFuture.supplyAsync(() -> calculatePrice(product), executor);
    }

    /**
     * 当异步方法出现异常，抛出内部异常
     *
     * @param product
     * @return
     */
    public Future<Double> getPriceAsyncExceptionally(String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();

        executor.execute(() -> {
            try {
                // 如果价格计算正常结束，完成Future操作并设置商品价格
                future.complete(calculatePrice(product));
            } catch (Exception e) {
                // 出现异常，就抛出导致失败的异常，完成这次Future操作
                future.completeExceptionally(e);
            }
        });
        return future;
    }

    private static double calculatePrice(String product) {
        // 耗时的查询数据库，获取价格
//        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 模拟1秒的延迟
     */
    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doSomeThingElse() {
        System.out.println("Handling other tasks...");
    }

    /**
     * 采用顺序流查询所有商店的方式实现
     *
     * @param product
     * @return
     */
    public static List<String> findPrices(String product) {
        return getShops().stream().map(shop -> String.format("%s price is %.2f\n", shop.getName(), shop.getPriceAsDouble(product)))
                .collect(toList());
    }

    /**
     * 采用并行流查询所有商店的方式实现， 比顺序流快了4倍
     *
     * @param product
     * @return
     */
    public static List<String> findPricesAsParallel(String product) {
        return getShops().parallelStream().map(shop -> String.format("%s price is %.2f\n", shop.getName(), shop.getPriceAsDouble(product)))
                .collect(toList());
    }

    /**
     * 使用CompletableFuture异步查询，《效果不明显，比并行流要慢》，用定制的线程池，替换默认的ForkJoinPool
     * 关于并行流和异步的选择：
     *      计算密集型的操作，并且没有I/O，推荐使用stream/parallelStream
     *      反之，如果你并行的工作单元涉及等待I/O的操作（包括网络连接等待），那么使用CompletableFuture更加灵活
     *
     * @param product
     * @return
     */
    public static List<String> findPricesAsAsync(String product) {
        List<CompletableFuture<String>> futures = getShops().stream()
                // 使用异步的方式计算每种商品价格
                .map(shop -> CompletableFuture.supplyAsync(() ->
                shop.getName() + " price is " + shop.getPriceAsDouble(product), executor)).collect(toList());
        // 等待所有异步任务操作结束
        return futures.stream().map(CompletableFuture::join).collect(toList());
    }

    /**
     * 使用Discount折扣服务响应，同步执行
     * @param product
     * @return
     */
    public static List<String> findPricesAsDiscount(String product) {
        // 获取商品
        return getShops().stream()
                // 取的每个shop对象中商品的原始价格
                .map(shop -> shop.getPrice(product))
                // 在Quote对象中对shop返回的字符串转换
                .map(Quote::parse)
                // 联系Discount服务，为每个Quote申请折扣
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    /**
     * 对多个异步任务进行流水线操作
     * 使用Discount折扣服务响应，异步执行，性能提升较明显
     * @param product
     * @return
     */
    public static List<String> findPricesAsDiscountAsync(String product) {
        List<CompletableFuture<String>> futures = getShops().stream()
                // 以异步的方式获得每个shop中指定产品的原始价格
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                // Quote对象存在时，对其返回的值进行转换
                .map(future -> future.thenApply(Quote::parse))
                // 使用另一个异步任务构造期望的Future，申请折扣
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)
                )).collect(toList());
        // 等待流中的所有Future执行完毕，并提取各自的返回值
        return futures.stream().map(CompletableFuture::join).collect(toList());
    }

    /**
     * 查询汇率价格，合并两个独立的CompletableFuture对象
     * @param product
     * @return
     */
    public static List<Double> findPriceAsRate(String product) {
        List<CompletableFuture<Double>> futures = getShops().stream()
                // 创建第一个异步任务，获取查询商品价格
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceAsDouble(product))
                .thenCombine(
                        // 创建第二个独立异步任务，查询美元和欧元之间的转换汇率
                        CompletableFuture.supplyAsync(() -> ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD)),
                        // 通过乘法整合得到的商品价格和利率
                        (price, rate) -> price * rate))
                .collect(toList());
        return futures.stream().map(CompletableFuture::join).collect(toList());
    }

    /**
     * 利用java7的方法合并两个Future对象
     * @param product
     * @return
     */
    public static Double findPriceMergeAsJava7(String product) throws ExecutionException, InterruptedException {
        // 创建一个查询欧元到美元转换汇率的Future
        final Future<Double> futureRate = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return ExchangeService.getRate(ExchangeService.Money.EUR, ExchangeService.Money.USD);
            }
        });
        // 创建第二个任务查询价格和计算汇率的Future
        Future<Double> futurePriceInUsd = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return getPriceAsDouble(product) * futureRate.get();
            }
        });
        // 返回计算出来的汇率价格
        return futurePriceInUsd.get();
    }

    /**
     * 响应CompletableFuture的completion事件
     * thenAccept
     * @param product
     * @return
     */
    public static Stream<CompletableFuture<String>> findPricesStream(String product) {
        return getShops().stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
    }

    /**
     * 商品列表
     * @return
     */
    public static List<Shop> getShops() {
        return Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"));
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        Runtime.getRuntime().exec("cmd /k start www.baidu.com");

        long start = System.nanoTime();

        // 响应CompletableFuture的completion事件
        CompletableFuture[] futures = findPricesStream("BestPrice").map(f -> f.thenAccept(System.out::println))
        .toArray(size -> new CompletableFuture[size]);
        // allOf等待CompletableFuture数组中的所有任务执行完成，返回CompletableFuture<Void>对象
//        CompletableFuture.allOf(futures).join();

        CompletableFuture.anyOf(futures).join();

//        System.out.println(findPriceAsRate("MyFavoriteShop"));
//        System.out.println(findPricesAsDiscountAsync("MyFavoriteShop"));
        long duration = (System.nanoTime() - start) / 1_000_000;

        System.out.println("duration = " + duration);
    }

}
