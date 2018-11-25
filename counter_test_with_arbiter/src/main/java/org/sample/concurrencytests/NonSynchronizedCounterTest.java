package org.sample.concurrencytests;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.I_Result;
import org.sample.counters.Counter;
import org.sample.counters.NonSynchronizedIntCounter;


@JCStressTest
@Outcome(id = "-1", expect = Expect.ACCEPTABLE_INTERESTING, desc = "One update lost (increment not done): atomicity failure.")
@Outcome(id = "0", expect = Expect.ACCEPTABLE, desc = "Actors updated independently.")
@Outcome(id = "1", expect = Expect.ACCEPTABLE_INTERESTING, desc = "One update lost (decrement not done): atomicity failure.")
@State
public class NonSynchronizedCounterTest {

    Counter counter = new NonSynchronizedIntCounter();

    @Actor
    public void actor1() {
        counter.increment();
    }

    @Actor
    public void actor2() {
        counter.decrement();
    }

    @Arbiter
    public void arbiter(I_Result r) {
        r.r1 = (int) counter.getValue();
    }

}
