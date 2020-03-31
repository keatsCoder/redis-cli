package cn.keats.rediscli.laoqian;

/**
 * @Author: keats_coder
 * @Date: 2020/03/31
 * @Version 1.0
 */
public class Holder<T> {
    private T value;

    public Holder() {
    }

    public Holder(T value) {
        this.value = value;
    }

    public void value(T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }
}
