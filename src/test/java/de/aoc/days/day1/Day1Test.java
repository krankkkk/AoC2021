package de.aoc.days.day1;

import de.aoc.days.Day;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day1Test {

    private final Day day = new Day1();

    @Test
    void part1Debug() {
        Assertions.assertEquals(7, day.part1(day.getDebugInput()));
    }

    @Test
    void part1Actual() {
        Assertions.assertEquals(1688, day.part1(day.getActualInput()));
    }

    @Test
    void part2Debug() {
        Assertions.assertEquals(5, day.part2(day.getDebugInput()));
    }

    @Test
    void part2Actual() {
        Assertions.assertEquals(1728, day.part2(day.getActualInput()));
    }
}
