package de.aoc.days;

import java.util.stream.Stream;

public interface Day {

    long part1();

    long part1(Stream<String> input);

    long part2();

    long part2(Stream<String> strings);

    Stream<String> getDebugInput();

    Stream<String> getActualInput();
}
