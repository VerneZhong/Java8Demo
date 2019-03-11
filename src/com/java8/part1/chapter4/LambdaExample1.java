package com.java8.part1.chapter4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Mr.zxb
 * @date 2019-03-01 13:37
 */
public class LambdaExample1 {

    /**
     * 读取一行
     * @return
     * @throws IOException
     */
    public static String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("d:/data.txt"))) {
            return br.readLine();
        }
    }

    /**
     * 让行为参数化
     * @param bufferedReaderProcessor
     * @return
     * @throws IOException
     */
    public static String processFile2(BufferedReaderProcessor bufferedReaderProcessor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("d:/data.txt"))) {
            return bufferedReaderProcessor.process(br);
        }
    }

    public static void main(String[] args) throws IOException {
        String oneLine = processFile2(br -> br.readLine());

        String twoLine = processFile2(br -> br.readLine() + br.readLine());
    }
}
