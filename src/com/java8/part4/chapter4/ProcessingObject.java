package com.java8.part4.chapter4;

/**
 * 责任链模式
 * @author Mr.zxb
 * @date 2019-04-04 15:01
 */
public abstract class ProcessingObject<T> {

    protected ProcessingObject<T> successor;

    public void setSuccessor(ProcessingObject<T> successor) {
        this.successor = successor;
    }

    /**
     * 提供了如何进行工作处理的框架
     * @param input
     * @return
     */
    public T handle(T input) {
        T r = handleWork(input);
        if (successor != null) {
            return successor.handle(r);
        }
        return r;
    }

    abstract protected T handleWork(T input);
}
