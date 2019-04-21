package com.java8.part10.bytecode;

import java.util.function.Function;

/**
 * 匿名类生成字节码：
 * 编译器会为每一个匿名类生成呀一个新的.class文件，以className$1形式呈现，其中className是类的名字，紧跟着一个美元符号和一个数字，
 * 生成大量的类文件是对性能不利的，因为每个类文件使用之前都需要加载和验证，会直接影响到应用的启动性能。
 *
 * 每个新的匿名类都会为类或者接口产生一个新的子类型。
 *
 * 可以通过命令 javap -c -v className 生成字节码文件
 *
 * 以匿名内部类的方式实现的一个Function接口
 * @author Mr.zxb
 * @date 2019-04-21 11:17:35
 */
public class AnonymousClassBytecode {

    Function<Object, String> function = new Function<Object, String>() {
        @Override
        public String apply(Object o) {
            return o.toString();
        }

    };

}
