package de.aoc.days;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class DayTest {

    protected final Day day;
    protected final int part1Debug;
    protected final int part2Debug;
    protected final int part1Actual;
    protected final int part2Actual;

    protected DayTest(Day day, int part1Debug, int part2Debug, int part1Actual, int part2Actual) {
        this.day = day;
        this.part1Debug = part1Debug;
        this.part2Debug = part2Debug;
        this.part1Actual = part1Actual;
        this.part2Actual = part2Actual;
    }

    @Test
    void part1Debug() {
        Assertions.assertEquals(this.part1Debug, day.part1(day.getDebugInput()));
    }

    @Test
    void part1Actual() {
        Assertions.assertEquals(this.part1Actual, day.part1(day.getActualInput()));
    }

    @Test
    void part2Debug() {
        Assertions.assertEquals(this.part2Debug, day.part2(day.getDebugInput()));
    }

    @Test
    void part2Actual() {
        Assertions.assertEquals(this.part2Actual, day.part2(day.getActualInput()));
    }
}
