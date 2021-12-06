package de.aoc.days.day6;

import de.aoc.days.AbstractDay;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day6 extends AbstractDay {
    @Override
    public long part1(Stream<String> stream) {
        return iterate(stream, 80);
    }

    private long iterate(Stream<String> stream, final int iterateCount) {
        Map<Integer, Long> fish = stream
                .map(line -> line.split(","))
                .flatMap(Arrays::stream)
                .filter(Predicate.not(String::isBlank))
                .map(Integer::parseInt)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        fillNullFields(fish);

        for (int i = 0; i < iterateCount; i++) {
            Map<Integer, Long> newFish = fillNullFields(new HashMap<>());
            fish.forEach((key, value) -> {
                if (key == 0) {
                    newFish.put(6, newFish.get(6) + value);
                    newFish.put(8, value);
                } else {
                    newFish.put(key - 1, newFish.get(key - 1) + value);
                }
            });
            fish = newFish;
        }

        return fish.values()
                .stream()
                .mapToLong(Long::longValue)
                .sum();
    }

    private Map<Integer, Long> fillNullFields(final Map<Integer, Long> fish) {
        IntStream.rangeClosed(0, 8).forEach(c -> fish.putIfAbsent(c, 0L));
        return fish;
    }

    @Override
    public long part2(Stream<String> stream) {
        return iterate(stream, 256);
    }
}
