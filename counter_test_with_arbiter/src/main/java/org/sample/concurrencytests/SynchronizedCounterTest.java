/*
 * Copyright (c) 2016, Red Hat Inc.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.sample.concurrencytests;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.I_Result;
import org.sample.counters.Counter;
import org.sample.counters.SynchronizedIntCounter;


@JCStressTest
@Outcome(id = "-1", expect = Expect.ACCEPTABLE_INTERESTING, desc = "One update lost (increment not done): atomicity failure.")
@Outcome(id = "0", expect = Expect.ACCEPTABLE, desc = "Actors updated independently.")
@Outcome(id = "1", expect = Expect.ACCEPTABLE_INTERESTING, desc = "One update lost (decrement not done): atomicity failure.")
@State
public class SynchronizedCounterTest {

    Counter counter = new SynchronizedIntCounter();

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
