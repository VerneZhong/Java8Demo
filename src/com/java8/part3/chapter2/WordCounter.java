package com.java8.part3.chapter2;

/**
 * 用来在遍历Character流时计数的类
 * @author Mr.zxb
 * @date 2019-04-01 17:44
 */
public class WordCounter {

    private final int counter;

    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character character) {
        // 和迭代算法一样，accumulate方法一个个遍历Character
        if (Character.isWhitespace(character)) {
            return lastSpace ? this : new WordCounter(counter, true);
        } else {
            // 上一个字符是空格，而当前遍历的字符不是空格时，将单词计数器加1
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int getCounter() {
        return this.counter;
    }
}
