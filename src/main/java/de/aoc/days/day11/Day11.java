package de.aoc.days.day11;

import de.aoc.days.AbstractDay;

import java.util.Arrays;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Day11 extends AbstractDay {
    @Override
    public long part1(Stream<String> stream) {
        final Fish[][] arr = toArray(stream);

        return LongStream.range(0, 100)
                .map(i -> iterate(arr))
                .sum();
    }

    @Override
    public long part2(Stream<String> stream) {
        final Fish[][] arr = toArray(stream);
        final int fishCount = arr.length * arr[0].length;

        return LongStream.iterate(1, i -> true, i -> i + 1)
                .filter(i -> iterate(arr) == fishCount)
                .findFirst()
                .orElseThrow();
    }

    private void doStep(Fish[][] arr, int x, int y) {
        if (x < 0 || y < 0 || y >= arr.length || x >= arr[y].length) {
            return;
        }

        final Fish current = arr[y][x];
        current.setLevel(current.getLevel() + 1);

        if (current.getLevel() >= 10 && !current.isFlashing()) {
            current.setFlashing(true);
            doStep(arr, x - 1, y);//Left
            doStep(arr, x + 1, y);//Right
            doStep(arr, x, y - 1);//Top
            doStep(arr, x, y + 1);//Bottom
            doStep(arr, x + 1, y + 1);//Bottom-Right
            doStep(arr, x - 1, y + 1);//Bottom-Left
            doStep(arr, x - 1, y - 1);//Top-Left
            doStep(arr, x + 1, y - 1);//Top-Right
        }
    }

    private long iterate(Fish[][] arr) {
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[y].length; x++) {
                doStep(arr, x, y);
            }
        }

        return Arrays.stream(arr)
                .flatMap(Arrays::stream)
                .filter(Fish::isFlashing)
                .peek(current -> {
                    current.setLevel(0);
                    current.setFlashing(false);
                })
                .count();
    }

    private static Fish[][] toArray(final Stream<String> stream) {
        final int[][] array = stream.map(line -> line.split(""))
                .map(arr -> Arrays.stream(arr).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);

        final Fish[][] points = new Fish[array.length][];
        for (int y = 0; y < array.length; y++) {
            points[y] = new Fish[array[y].length];
            for (int x = 0; x < array[y].length; x++) {
                points[y][x] = new Fish(array[y][x]);
            }
        }
        return points;
    }

}
