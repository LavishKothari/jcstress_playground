package org.sample.counters.concurrencytests;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;
import org.sample.counters.Counter;
import org.sample.counters.NonSynchronizedIntCounter;


@JCStressTest
@Outcome(id = "1, 1", expect = Expect.ACCEPTABLE_INTERESTING, desc = "One update lost (increment not done): atomicity failure.")
@Outcome(id = "1, 2", expect = Expect.ACCEPTABLE, desc = "Actors updated independently.")
@Outcome(id = "2, 1", expect = Expect.ACCEPTABLE, desc = "Actors updated independently.")
@Outcome(id = "2, 2", expect = Expect.ACCEPTABLE, desc = "Actors updated independently - Interleaving of actors")
@State
public class NonSynchronizedCounterTest {

    Counter counter = new NonSynchronizedIntCounter();

    @Actor
    public void actor1(II_Result r) {
        counter.increment();
        r.r1 = (int) counter.getValue();
    }

    @Actor
    public void actor2(II_Result r) {
        counter.increment();
        r.r2 = (int) counter.getValue();
    }


}
