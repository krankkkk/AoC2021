package de.aoc.days.day5;

import de.aoc.days.AbstractDay;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day5 extends AbstractDay {
    @Override
    public long part1(Stream<String> stream) {
        return getPart1(getInitialValues(stream).sLines).count;
    }

    private Part1 getPart1(List<int[]> sLines) {
        final int girdSize = 1000;
        final int[][] grid = new int[girdSize][girdSize];
        int count = 0;
        for (var l : sLines) {
            int y1 = Math.min(l[1], l[3]);
            int y2 = Math.max(l[1], l[3]);
            int x1 = Math.min(l[0], l[2]);
            int x2 = Math.max(l[0], l[2]);
            for (int y = y1; y <= y2; y++) {
                for (int x = x1; x <= x2; x++) {
                    grid[y][x]++;
                    if (grid[y][x] == 2) {
                        count++;
                    }
                }
            }
        }
        return new Part1(grid, count);
    }

    private record Part1(int[][] grid, int count) {
    }

    private InitialValues getInitialValues(Stream<String> stream) {
        final List<int[]> dLines = new ArrayList<>();
        final List<int[]> sLines = new ArrayList<>();

        for (var s : stream.toList()) {
            String[] sar = s.split("(,|( -> ))");
            int[] arr = new int[4];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(sar[i]);
            }
            if (arr[0] == arr[2] || arr[1] == arr[3]) {
                sLines.add(arr);
            } else {
                dLines.add(arr);
            }
        }
        return new InitialValues(dLines, sLines);
    }

    private record InitialValues(List<int[]> dLines, List<int[]> sLines) {
    }

    @Override
    public long part2(Stream<String> stream) {
        final InitialValues initialValues = getInitialValues(stream);
        Part1 part1 = getPart1(initialValues.sLines);
        int[][] grid = part1.grid;

        int count = 0;
        for (final int[] l : initialValues.dLines) {
            int xm = l[1] < l[3] ? 1 : -1;
            int ym = l[0] < l[2] ? 1 : -1;
            for (int i = 0; i <= Math.abs(l[0] - l[2]); i++) {
                grid[l[1] + (i * xm)][l[0] + (i * ym)]++;
                if (grid[l[1] + (i * xm)][l[0] + (i * ym)] == 2) {
                    count++;
                }
            }
        }
        return count + part1.count();
    }
}
