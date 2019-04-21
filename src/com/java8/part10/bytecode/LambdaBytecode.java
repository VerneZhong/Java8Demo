package com.java8.part10.bytecode;

import java.util.function.Function;

/**
 * Lambda生成字节码
 * 用字节码指令InvokeDynamic， InvokeDynamic最初Java7引入，用于支持运行于JVM上的动态语言，执行方法调用时，
 * InvokeDynamic添加了更高层的抽象
 *
 * Lambda表达式的代码块到字节码的转化由高层的策略变成了纯粹的实现细节。它现在可以动态地改变，或者未来版本得到优化，修改，并且向后兼容。
 * 没有带来额外的开销，没有额外的字段，也不需要进行静态初始化，而这些不使用lambda，就不会实现。
 * 对无状态非捕获型Lambda，我们可以创建一个 Lambda对象的实例，对其进行缓存，之后对同一对象的访问都返回同意的内容。
 * 没有额外的性能开销，因为这些转化都是必须的，并且结果也进行了链接，仅在lambda首次被调用的时需要转换，其后所有的调用都能直接跳过这一步，直接
 * 调用之前的链接的实现。
 *
 * 代码生成策略：
 * 将Lambda表达式的代码体填入到运行时动态创建的静态方法，就完成了Lambda表达式的字节码转换。
 * @author Mr.zxb
 * @date 2019-04-21 11:17:12
 */
public class LambdaBytecode {

    Function<Object, String> function = obj -> obj.toString();


    // 代码生成策略 示例
//    Function<Object, String> function = [ dynamic invocation of lambda$1]
// 更多关于Lambda表达式转换流程的内容，可以访问如下地址：http://cr.openjdk.java.net/~briangoetz/lambda/lambda-translation.html
    static String lambda$1(Object object) {
        return object.toString();
    }
}
