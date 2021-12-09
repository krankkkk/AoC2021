package de.aoc.days;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

public abstract class DayTest {

    protected final Day day;
    protected final long part1Debug;
    protected final long part2Debug;
    protected final long part1Actual;
    protected final long part2Actual;

    protected DayTest(Day day, long part1Debug, long part2Debug, long part1Actual, long part2Actual) {
        this.day = day;
        this.part1Debug = part1Debug;
        this.part2Debug = part2Debug;
        this.part1Actual = part1Actual;
        this.part2Actual = part2Actual;
    }

    @Test
    void part1Debug() {
        final long actual = day.part1(day.getDebugInput());
        Assumptions.assumeTrue(actual != -1);
        Assertions.assertEquals(this.part1Debug, actual);
    }

    @Test
    void part1Actual() {
        final long actual = day.part1(day.getActualInput());
        Assumptions.assumeTrue(actual != -1);
        Assertions.assertEquals(this.part1Actual, actual);
    }

    @Test
    void part2Debug() {
        final long actual = day.part2(day.getDebugInput());
        Assumptions.assumeTrue(actual != -1);
        Assertions.assertEquals(this.part2Debug, actual);
    }

    @Test
    void part2Actual() {
        final long actual = day.part2(day.getActualInput());
        Assumptions.assumeTrue(actual != -1);
        Assertions.assertEquals(this.part2Actual, actual);
    }
}
