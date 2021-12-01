package de.aoc.days.day1;

import de.aoc.days.AbstractDay;

import java.util.LinkedList;
import java.util.List;
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

        final List<Integer> windows = new LinkedList<>();
        for (int i = 0; i < input.size() - 2; i++) {
            windows.add(input.get(i) + input.get(i + 1) + input.get(i + 2));
        }
        return count(windows);
    }

    private long count(final List<Integer> windows) {
        int bigger = 0;
        for (int i = 0; i < windows.size() - 1; i++) {
            if (windows.get(i) < windows.get(i + 1)) {
                bigger++;
            }
        }
        return bigger;
    }
}
