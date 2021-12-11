package de.aoc.days.day8;

import de.aoc.days.AbstractDay;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8 extends AbstractDay {

    private static final int SEGMENTS_IN_ONE = 2;
    private static final int SEGMENTS_IN_FOUR = 4;
    private static final int SEGMENTS_IN_SEVEN = 3;
    private static final int SEGMENTS_IN_EIGHT = 7;


    @Override
    public long part1(Stream<String> stream) {
        return stream.map(line -> line.substring(line.indexOf('|') + 1))
                .flatMap(line -> Arrays.stream(line.split("\\s")))
                .mapToInt(String::length)
                .filter(i -> i == SEGMENTS_IN_ONE
                        || i == SEGMENTS_IN_FOUR
                        || i == SEGMENTS_IN_SEVEN
                        || i == SEGMENTS_IN_EIGHT)
                .count();
    }

    //No credits to me, this is copied from https://github.com/Suicolen
    @Override
    public long part2(Stream<String> stream) {
        List<Data> input = stream
                .map(line -> {
                    String[] data = line.split(" \\| ");
                    return new Data(data[0].split(" "), data[1].split(" "));
                })
                .toList();

        long sum = 0;
        String[] digits = new String[10];
        for (Data data : input) {
            String[] patterns = data.patterns;
            String[] output = data.output;
            getKnownValues(digits, patterns);
            findOfLengthSix(digits, patterns);
            findOfLengthFive(digits, patterns);

            for (int i = 0; i < 10; i++) {
                digits[i] = sort(digits[i]);
            }

            sum += compute(output, digits);
        }
        return sum;
    }

    private int compute(String[] output, String[] sortedDigits) {
        int number = 0;
        for (String s : output) {
            String sorted = sort(s);
            for (int i = 0; i < 10; i++) {
                if (sortedDigits[i].equals(sorted)) {
                    number = (number * 10) + i;
                }
            }
        }
        return number;
    }

    private void findOfLengthSix(String[] digits, String[] patterns) {
        for (String pattern : patterns) {
            if (pattern.length() == 6) {
                if (isNine(digits, pattern)) {
                    digits[9] = pattern;
                } else if (isZeroOrThree(digits, pattern)) {
                    digits[0] = pattern;
                } else {
                    digits[6] = pattern;
                }
            }
        }
    }

    private void findOfLengthFive(String[] digits, String[] patterns) {
        for (String pattern : patterns) {
            if (pattern.length() == 5) {
                if (isZeroOrThree(digits, pattern)) {
                    digits[3] = pattern;
                } else if (isFive(digits, pattern)) {
                    digits[5] = pattern;
                } else {
                    digits[2] = pattern;
                }
            }
        }
    }

    private void getKnownValues(String[] digits, String[] patterns) {
        for (String pattern : patterns) {
            switch (pattern.length()) {
                case SEGMENTS_IN_ONE -> digits[1] = pattern;
                case SEGMENTS_IN_FOUR -> digits[4] = pattern;
                case SEGMENTS_IN_SEVEN -> digits[7] = pattern;
                case SEGMENTS_IN_EIGHT -> digits[8] = pattern;
            }
        }
    }

    private String sort(String digits) {
        return toCharStream(digits)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private boolean isNine(String[] digits, String patterns) {
        return toCharStream(digits[4])
                .map(String::valueOf)
                .allMatch(patterns::contains);
    }


    private boolean isZeroOrThree(String[] digits, String patterns) {
        return toCharStream(digits[1])
                .map(String::valueOf)
                .allMatch(patterns::contains);
    }

    private boolean isFive(String[] digits, String patterns) {
        return toCharStream(digits[6])
                .map(String::valueOf)
                .filter(Predicate.not(patterns::contains))
                .count() == 1;
    }

    private Stream<Character> toCharStream(String str) {
        return str.chars().mapToObj(c -> (char) c);
    }

    private record Data(String[] patterns, String[] output) {
    }
}
