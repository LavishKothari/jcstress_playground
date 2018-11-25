package org.sample.counters;

public interface Counter<T> {

    void increment();

    void decrement();

    T getValue();
}
