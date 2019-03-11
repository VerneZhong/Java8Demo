package com.java8.part1.chapter1;

import java.io.File;
import java.io.FileFilter;

/**
 * @author Mr.zxb
 * @date 2019-01-31 14:08
 */
public class Example1 {

    public static void main(String[] args) {
        // old java
        File[] hiddenFilesOld = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isHidden();
            }
        });

        // new java 8 lambda 方法引用
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
    }
}
