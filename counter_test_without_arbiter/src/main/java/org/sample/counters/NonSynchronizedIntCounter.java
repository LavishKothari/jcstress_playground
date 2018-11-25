package org.sample.counters;

// no synchronization done
public class NonSynchronizedIntCounter implements Counter<Integer> {

    private int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public void decrement() {
        value--;
    }

    @Override
    public Integer getValue() {
        return Integer.valueOf(value);
    }
}
