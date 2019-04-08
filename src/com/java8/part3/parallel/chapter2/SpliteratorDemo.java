package com.java8.part3.parallel.chapter2;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 工作窃取，实现自己的Spliterator
 * @author Mr.zxb
 * @date 2019-04-01 17:02
 */
public class SpliteratorDemo {

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            // 逐个遍历String中的所有字符
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    counter++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }

    public static void main(String[] args) {

        final String SENTENCE = " Nel    mezzo del cammin   di  nostra vita" +
                "mi ritrovai in una  selva oscura" +
                " che la  dritta via era   smarrita ";

        System.out.println("found " + countWordsIteratively(SENTENCE));

        // 以函数式风格重写单词计数器
        Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
//        System.out.println("countWords Found = " + countWords(stream));
        // 并行出现不一致，拆分的顺序导致的
        System.out.println("parallel countWords Found = " + countWords(stream.parallel()));

        Stream<Character> stream2 = StreamSupport.stream(new WordCounterSpliterator(SENTENCE), true);
        System.out.println("parallel2 countWords Found = " + countWords(stream2));

    }

    public static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();
    }
}
