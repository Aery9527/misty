package org.misty.util.tool.cycle;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntPredicate;

public class CycleInt {

    private final int start;

    private final int limit;

    private final int step;

    private final AtomicInteger current;

    private final IntPredicate limitReferee;

    public CycleInt(int start, int limit) {
        this(start, limit, 1);
    }

    public CycleInt(int start, int limit, int step) {
        this.start = start;
        this.limit = limit;
        this.step = step;
        this.current = new AtomicInteger(this.start);

        boolean stepPositive = step > 0;
        boolean rangePositive = start < limit;

        if (stepPositive) {
            this.limitReferee = (number) -> number <= this.limit;
        } else {
            this.limitReferee = (number) -> number >= this.limit;
        }

        if (step == 0) {
            throw new IllegalArgumentException("step can't be 0");
        } else if (start == limit) {
            throw new IllegalArgumentException("start(" + start + ") and limit(" + limit + ") can't be the same");
        }

        if (stepPositive) {
            if (!rangePositive) {
                throw new IllegalArgumentException("cycle never work because step(" + step + ") is a positive number but start(" + start + ") > limit(" + limit + ")");
            }
        } else {
            if (rangePositive) {
                throw new IllegalArgumentException("cycle never work because step(" + step + ") is a negative number but start(" + start + ") < limit(" + limit + ")");
            }
        }
    }

    public int getCurrentAndNext() {
        int current = this.current.getAndAdd(this.step);
        if (this.limitReferee.test(current)) {
            return current;
        }

        synchronized (this.current) {
            current = this.current.getAndAdd(this.step);
            if (this.limitReferee.test(current)) {
                return current;
            }

            this.current.set(this.start);
            return this.current.getAndAdd(this.step);
        }
    }

    public int getStart() {
        return start;
    }

    public int getLimit() {
        return limit;
    }

    public int getStep() {
        return step;
    }

}
