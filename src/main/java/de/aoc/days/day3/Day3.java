package de.aoc.days.day3;

import de.aoc.days.AbstractDay;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 extends AbstractDay {

    @Override
    public long part1(Stream<String> stream) {
        final char[][] chars = stream.map(String::toCharArray)
                .toArray(char[][]::new);

        final Map<Integer, AtomicInteger> positions = getPosCount(chars);
        final String start = "0".repeat(chars[0].length);
        final StringBuilder mostSB = new StringBuilder(start);
        final StringBuilder leastSB = new StringBuilder(start);

        for (final int key : positions.keySet()) {
            final boolean isMoreCommon = positions.get(key).intValue() >= (chars.length / 2);
            mostSB.setCharAt(key, isMoreCommon ? '1' : '0');
            leastSB.setCharAt(key, isMoreCommon ? '0' : '1');
        }

        int gamma = Integer.parseInt(mostSB.toString(), 2);
        int epsilon = Integer.parseInt(leastSB.toString(), 2);

        return gamma * epsilon;
    }

    private Map<Integer, AtomicInteger> getPosCount(char[][] chars) {
        final Map<Integer, AtomicInteger> positions = new HashMap<>();

        for (char[] current : chars) {
            for (int j = 0; j < current.length; j++) {
                if (current[j] == '1') {
                    positions.computeIfAbsent(j, k -> new AtomicInteger(0))
                            .incrementAndGet();
                }
            }
        }
        return positions;
    }

    @Override
    public long part2(Stream<String> stream) {
        final Set<String> all = stream.collect(Collectors.toSet());

        final Set<String> most = new HashSet<>(all);
        final Set<String> least = new HashSet<>(all);

        int currentPos = 0;
        while (most.size() > 1 || least.size() > 1) {
            final int finalCurrentPos = currentPos;
            if (most.size() > 1) {
                char mostCommon = getMostCommon(most, finalCurrentPos);
                most.removeIf(str -> str.charAt(finalCurrentPos) != mostCommon);
            }

            if (least.size() > 1) {
                char leastCommon = getLeastCommon(least, finalCurrentPos);
                least.removeIf(str -> str.charAt(finalCurrentPos) != leastCommon);
            }

            currentPos++;
        }

        int oxy = Integer.parseInt(most.iterator().next(), 2);
        int co2 = Integer.parseInt(least.iterator().next(), 2);
        return oxy * co2;
    }

    private char getMostCommon(Set<String> most, int currentPos) {
        long count = most.stream()
                .map(String::toCharArray)
                .filter(ch -> ch[currentPos] == '1')
                .count();

        return count >= Math.ceil(most.size() / 2d) ? '1' : '0';
    }

    private char getLeastCommon(Set<String> least, int currentPos) {
        long count = least.stream()
                .map(String::toCharArray)
                .filter(ch -> ch[currentPos] == '1')
                .count();

        return count >= Math.ceil(least.size() / 2d) ? '0' : '1';
    }
}
