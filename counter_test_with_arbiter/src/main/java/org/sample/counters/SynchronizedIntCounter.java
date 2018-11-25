package org.sample.counters;

// explicit synchronization done
public class SynchronizedIntCounter implements Counter<Integer> {

    // volatile for avoiding the visibility issues
    private volatile int value;

    @Override
    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    @Override
    public void decrement() {
        synchronized (this) {
            value--;
        }
    }

    @Override
    public Integer getValue() {
        /*
         * no explicit synchronization needed here
         * as visibility issues of 'value' is taken care of,
         * as value is volatile, so multiple threads can do a
         * simultaneous get
         */
        return Integer.valueOf(value);
    }
}
