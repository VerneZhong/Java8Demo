package com.java8.part1.lambda.chapter4;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 用函数式接口传递行为
 * @author Mr.zxb
 * @date 2019-03-01 13:39
 */
@FunctionalInterface
public interface BufferedReaderProcessor {

    String process(BufferedReader bufferedReader) throws IOException;
}
