package de.aoc.days.day10;

import de.aoc.days.AbstractDay;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

public class Day10 extends AbstractDay {
    public static final int VALUE_P = 3; //()
    public static final int VALUE_B = 57;//[]
    public static final int VALUE_S = 1197;//{}
    public static final int VALUE_L = 25137;//<>

    @Override
    public long part1(Stream<String> stream) {
        return stream.map(this::toErrValue)
                .mapToLong(a -> a[0])
                .sum();
    }

    private long[] toErrValue(final String line) {
        final Deque<Character> stack = new ArrayDeque<>();
        for (final char c : line.toCharArray()) {
            if (c == '(' || c == '[' || c == '{' || c == '<') {
                stack.push(c);
            } else switch (c) {
                case ')':
                    if (stack.pop() != '(') return new long[]{VALUE_P, 0};
                    break;
                case ']':
                    if (stack.pop() != '[') return new long[]{VALUE_B, 0};
                    break;
                case '}':
                    if (stack.pop() != '{') return new long[]{VALUE_S, 0};
                    break;
                case '>':
                    if (stack.pop() != '<') return new long[]{VALUE_L, 0};
                    break;
                default:
                    System.err.println("unexpected char: " + c);
            }
        }
        long ret = 0;
        while (!stack.isEmpty()) {
            final char c = stack.pop();
            switch (c) {
                case '(' -> ret = ret * 5 + 1;
                case '[' -> ret = ret * 5 + 2;
                case '{' -> ret = ret * 5 + 3;
                case '<' -> ret = ret * 5 + 4;
                default -> System.err.println("unexpected char: " + c);
            }
        }
        return new long[]{0, ret};
    }

    @Override
    public long part2(Stream<String> stream) {
        final long[] scores = stream.map(this::toErrValue)
                .filter(a -> a[0] == 0)
                .mapToLong(a -> a[1])
                .sorted()
                .toArray();
        return scores[scores.length / 2];
    }
}
