package de.aoc.days.day7;

import de.aoc.days.AbstractDay;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day7 extends AbstractDay {
    @Override
    public long part1(Stream<String> stream) {
        final int[] number = stream.flatMap(line -> Arrays.stream(line.split(",")))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        long median = getMedian(number);
        return IntStream.of(number)
                .mapToDouble(i -> i - median)
                .map(Math::abs)
                .mapToInt(d -> (int) d)
                .sum();
    }

    private long getMedian(int[] number) {
        if (number.length % 2 == 0) {
            return ((long) number[number.length / 2] + (long) number[number.length / 2 - 1]) / 2;
        }

        return number[number.length / 2];
    }

    @Override
    public long part2(Stream<String> stream) {
        final int[] number = stream.flatMap(line -> Arrays.stream(line.split(",")))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        long median = getMedian(number);
        int cost = calcCost(number, median);
        int direction = calcCost(number, median + 1) < cost ? +1 : -1;

        while (true) {
            int tempCost = calcCost(number, median + direction);
            if (tempCost <= cost) {
                cost = tempCost;
                median += direction;
            } else {
                return cost;
            }
        }
    }

    private int calcCost(int[] number, long median) {
        return IntStream.of(number)
                .mapToDouble(i -> i - median)
                .map(Math::abs)
                .mapToInt(d -> (int) d)
                .flatMap(steps -> IntStream.iterate(1, i -> i <= steps, i -> i + 1))
                .sum();
    }

}
