package de.aoc.days.day9;

import de.aoc.days.AbstractDay;

import java.util.*;
import java.util.stream.Stream;

public class Day9 extends AbstractDay {

    private static final int MAX_DANGERLEVEL = 10;

    @Override
    public long part1(Stream<String> stream) {
        return getLowPoints(toArray(stream))
                .mapToInt(Point::dangerLevel)
                .sum();
    }

    @Override
    public long part2(Stream<String> stream) {
        final Point[][] input = toArray(stream);

        return getLowPoints(input)
                .map(point -> toBasin(input, point))
                .map(Basin::points)
                .map(Collection::size)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(1, Math::multiplyExact);
    }

    private static Basin toBasin(final Point[][] map,
                                 final Point startPoint) {
        final Set<Point> inBasin = new HashSet<>();
        final Queue<Point> queue = new ArrayDeque<>();
        queue.add(startPoint);

        while (!queue.isEmpty()) {
            final Point current = queue.poll();
            if (inBasin.contains(current) || current.dangerLevel() == MAX_DANGERLEVEL) {
                continue;
            }

            inBasin.add(current);
            queue.addAll(getAdjoiningPoints(map, current));
        }

        return new Basin(inBasin);
    }

    private static Stream<Point> getLowPoints(final Point[][] input) {
        return Arrays.stream(input)
                .flatMap(Arrays::stream)
                .filter(currentPoint -> isLowPoint(input, currentPoint));
    }

    private static boolean isLowPoint(final Point[][] input,
                                      final Point current) {
        final int currentLevel = current.dangerLevel();

        return getAdjoiningPoints(input, current)
                .stream()
                .map(Point::dangerLevel)
                .noneMatch(level -> level <= currentLevel);
    }

    private static Collection<Point> getAdjoiningPoints(Point[][] map, Point current) {
        final Collection<Point> adjoiningPoints = new ArrayList<>();
        //Left
        if (current.x() > 0) {
            adjoiningPoints.add(map[current.y()][current.x() - 1]);
        }

        //Top
        if (current.y() > 0) {
            adjoiningPoints.add(map[current.y() - 1][current.x()]);
        }

        //Right
        if (current.x() + 1 < map[current.y()].length) {
            adjoiningPoints.add(map[current.y()][current.x() + 1]);
        }

        //Bottom
        if (current.y() + 1 < map.length) {
            adjoiningPoints.add(map[current.y() + 1][current.x()]);
        }

        return adjoiningPoints;
    }


    private static Point[][] toArray(final Stream<String> stream) {
        final int[][] array = stream.map(line -> line.split(""))
                .map(arr -> Arrays.stream(arr).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);

        final Point[][] points = new Point[array.length][array[0].length];
        for (int y = 0; y < array.length; y++) {
            for (int x = 0; x < array[y].length; x++) {
                points[y][x] = new Point(y, x, array[y][x] + 1);
            }
        }
        return points;
    }

    private record Basin(Set<Point> points) {
    }

    private record Point(int y, int x, int dangerLevel) {
    }
}
