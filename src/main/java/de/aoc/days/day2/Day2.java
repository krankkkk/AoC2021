package de.aoc.days.day2;

import de.aoc.days.AbstractDay;

import java.util.stream.Stream;

public class Day2 extends AbstractDay {

    @Override
    public long part1(final Stream<String> stream) {
        final Ship ship = new Ship();

        stream.forEachOrdered(ship::move);

        return ship.getResult();
    }

    @Override
    public long part2(final Stream<String> stream) {
        final Ship ship = new Ship2();

        stream.forEachOrdered(ship::move);

        return ship.getResult();
    }


    private static class Ship {
        protected long depth = 0;
        protected long pos = 0;

        public void move(final String str) {
            final String[] s = str.split("\\s");
            final int count = Integer.parseInt(s[1]);
            switch (s[0]) {
                case "forward" -> this.pos += count;
                case "down" -> this.depth -= count;
                case "up" -> this.depth += count;
                default -> throw new IllegalArgumentException(str);
            }
        }


        public long getResult() {
            return Math.abs(pos * depth);
        }
    }

    private static class Ship2 extends Ship {
        private long aim = 0;

        @Override
        public void move(final String str) {
            final String[] s = str.split("\\s");
            final int count = Integer.parseInt(s[1]);
            switch (s[0]) {
                case "forward" -> {
                    this.pos += count;
                    this.depth += this.aim * count;
                }
                case "down" -> this.aim += count;
                case "up" -> this.aim -= count;
                default -> throw new IllegalArgumentException(str);
            }
        }
    }
}
