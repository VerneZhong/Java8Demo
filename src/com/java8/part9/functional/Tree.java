package com.java8.part9.functional;

/**
 * 二叉查找树
 * @author Mr.zxb
 * @date 2019-04-18 10:13
 */
public class Tree {

    private String key;
    private int val;
    private Tree left, right;

    public Tree(String key, int val, Tree left, Tree right) {
        this.key = key;
        this.val = val;
        this.left = left;
        this.right = right;
    }

    static class TreeProcessor {

        /**
         * 二分树查找
         * @param k
         * @param defaultValue
         * @param t
         * @return
         */
        public static int lookup(String k, int defaultValue, Tree t) {
            if (t == null) {
                return defaultValue;
            }
            if (k.equals(t.key)) {
                return t.val;
            }
            return lookup(k, defaultValue, k.compareTo(t.key) < 0 ? t.left : t.right);
        }

        static void update(String k, int newVal, Tree t) {
            if (t == null) {
                // 新增一个节点
            } else if (k.equals(t.key)) {
                t.val = newVal;
            } else {
                update(k, newVal, k.compareTo(t.key) < 0 ? t.left : t.right);
            }
        }

        static Tree update2(String k, int newVal, Tree t) {
            if (t == null) {
                t = new Tree(k, newVal, null, null);
            } else if (k.equals(t.key)) {
                t.val = newVal;
            } else if (k.compareTo(t.key) < 0) {
                t.left = update2(k, newVal, t.left);
            } else {
                update2(k, newVal, t.right);
            }
            return t;
        }

        /**
         * 采用函数式的方法
         * @param k
         * @param newVal
         * @param t
         * @return
         */
        static Tree fupdate(String k, int newVal, Tree t) {
            return (t == null) ? new Tree(k, newVal, null, null) :
                    k.equals(t.key) ? new Tree(k, newVal, t.left, t.right) :
                    k.compareTo(t.key) < 0 ?
                    new Tree(t.key, t.val, fupdate(k, newVal, t.left), t.right) :
                    new Tree(t.key, t.val, t.left, fupdate(k ,newVal, t.right));
        }
    }

}
