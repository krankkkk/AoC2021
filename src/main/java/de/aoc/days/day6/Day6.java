package de.aoc.days.day6;

import de.aoc.days.AbstractDay;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Day6 extends AbstractDay {

    public static final int STATE_COUNT = 9;

    @Override
    public long part1(Stream<String> stream) {
        return iterate(stream, 80);
    }

    private long iterate(Stream<String> stream, final int iterateCount) {
        long[] fishPerDay = getArray(stream);

        for (int i = 0; i < iterateCount; i++) {
            final long[] newFish = new long[STATE_COUNT];
            for (int j = 0; j < fishPerDay.length; j++) {
                if (j == 0) {
                    newFish[6] = newFish[6] + fishPerDay[0];
                    newFish[8] = fishPerDay[0];
                } else {
                    newFish[j - 1] = newFish[j - 1] + fishPerDay[j];
                }
            }

            fishPerDay = newFish;
        }

        return LongStream.of(fishPerDay).sum();
    }

    private long[] getArray(Stream<String> stream) {
        Map<Integer, Long> map = stream.map(line -> line.split(","))
                .flatMap(Arrays::stream)
                .filter(Predicate.not(String::isBlank))
                .map(Integer::parseInt)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        final long[] fishPerDay = new long[STATE_COUNT];
        map.forEach((k, v) -> fishPerDay[k] = v);
        return fishPerDay;
    }

    @Override
    public long part2(Stream<String> stream) {
        return iterate(stream, 256);
    }
}
