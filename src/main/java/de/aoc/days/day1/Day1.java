package de.aoc.days.day1;

import de.aoc.days.AbstractDay;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day1 extends AbstractDay {

    @Override
    public long part1(final Stream<String> stream) {
        final List<Integer> input = stream
                .map(Integer::parseInt)
                .toList();

        return count(input);
    }

    @Override
    public long part2(final Stream<String> stream) {
        final List<Integer> input = stream
                .map(Integer::parseInt)
                .toList();

        final List<Integer> windows = IntStream.range(0, input.size() - 2)
                .mapToObj(i -> input.get(i) + input.get(i + 1) + input.get(i + 2))
                .toList();
        return count(windows);
    }

    private long count(final List<Integer> windows) {
        return IntStream.range(0, windows.size() - 1)
                .filter(i -> windows.get(i) < windows.get(i + 1))
                .count();
    }
}
