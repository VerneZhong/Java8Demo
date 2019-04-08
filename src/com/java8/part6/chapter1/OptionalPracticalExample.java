package com.java8.part6.chapter1;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

/**
 * @author Mr.zxb
 * @date 2019-04-08 10:34
 */
public class OptionalPracticalExample {

    public static void main(String[] args) {

        // 用Optional封装可能为null的值
        Map<String, Object> map = new HashMap<>(16);

        Object value = map.get("key");

        // 替代方式, 更安全
        Optional<Object> optional = Optional.ofNullable(map.get("key"));

        optional.orElse(value);
        optional.orElseGet(Object::new);

        // 异常与Optional的对比
        optional.orElseThrow(RuntimeException::new);

    }

    /**
     * 以命令式编程的方式从属性中读取duration的值
     * @param props
     * @param name
     * @return
     */
    public int readDuration(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    /**
     * 优雅的函数式方式
     * @param props
     * @param name
     * @return
     */
    public int readDurationAsOptional(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(OptionalUtil::strintToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }

    @Test
    public void test() {
        Properties properties = new Properties();
        properties.setProperty("a", "5");
        properties.setProperty("b", "true");
        properties.setProperty("c", "-3");

        assertEquals(5, readDurationAsOptional(properties, "a"));
        assertEquals(0, readDurationAsOptional(properties, "b"));
        assertEquals(0, readDurationAsOptional(properties, "c"));
        assertEquals(0, readDurationAsOptional(properties, "d"));
    }
}
