package de.aoc;

import de.aoc.days.Day;
import de.aoc.days.day1.Day1;
import de.aoc.days.day10.Day10;
import de.aoc.days.day11.Day11;
import de.aoc.days.day12.Day12;
import de.aoc.days.day2.Day2;
import de.aoc.days.day3.Day3;
import de.aoc.days.day4.Day4;
import de.aoc.days.day5.Day5;
import de.aoc.days.day6.Day6;
import de.aoc.days.day7.Day7;
import de.aoc.days.day8.Day8;
import de.aoc.days.day9.Day9;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {

    public static void main(String[] args) {
        final ExecutorService service = Executors.newCachedThreadPool();

        final Collection<Day> days = List.of(new Day1(), new Day2(), new Day3(), new Day4(), new Day5(), new Day6(),
                new Day7(), new Day8(), new Day9(), new Day10(), new Day11(), new Day12());
        for (Day day : days) {
            service.submit(() -> executeDay(day));
        }
        service.shutdown();
    }

    private static void executeDay(Day day) {
        try {
            final Instant start = Instant.now();
            final long part1 = day.part1();
            final long part2 = day.part2();
            final Duration time = Duration.between(start, Instant.now());
            System.out.printf("%s - %d     -       %d      %s ms%n", day.getClass().getSimpleName(), part1, part2, time.getNano() / 1000_000);
        } catch (final Exception e) {
            System.err.println(day.getClass().getSimpleName() + e.getMessage());
            e.printStackTrace();
        }
    }
}
