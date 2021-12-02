package de.aoc.days.day2;

import de.aoc.days.AbstractDay;

import java.util.stream.Stream;

public class Day2 extends AbstractDay {

    @Override
    public long part1(final Stream<String> stream) {
        final Ship ship = new Ship();

        stream.map(Command::parseCommand)
                .forEachOrdered(ship::move);

        return ship.getResult();
    }

    @Override
    public long part2(final Stream<String> stream) {
        final Ship ship = new Ship2();

        stream.map(Command::parseCommand)
                .forEachOrdered(ship::move);

        return ship.getResult();
    }

    private record Command(String order, int steps) {
        private static Command parseCommand(final String line) {
            final String[] s = line.split("\\s");
            return new Command(s[0], Integer.parseInt(s[1]));
        }
    }


    private static class Ship {
        protected long depth = 0;
        protected long pos = 0;

        public void move(final Command command) {
            switch (command.order) {
                case "forward" -> this.pos += command.steps;
                case "down" -> this.depth += command.steps;
                case "up" -> this.depth -= command.steps;
                default -> throw new IllegalArgumentException(command.toString());
            }
        }


        public long getResult() {
            return pos * depth;
        }
    }

    private static class Ship2 extends Ship {
        private long aim = 0;

        @Override
        public void move(final Command command) {
            switch (command.order) {
                case "forward" -> {
                    this.pos += command.steps;
                    this.depth += this.aim * command.steps;
                }
                case "down" -> this.aim += command.steps;
                case "up" -> this.aim -= command.steps;
                default -> throw new IllegalArgumentException(command.toString());
            }
        }
    }
}
