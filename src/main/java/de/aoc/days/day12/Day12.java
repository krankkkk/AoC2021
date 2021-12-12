package de.aoc.days.day12;

import de.aoc.days.AbstractDay;

import java.util.*;
import java.util.stream.Stream;

public class Day12 extends AbstractDay {
    private static final String START_NODE = "start";
    private static final String END_NODE = "end";

    @Override
    public long part1(Stream<String> stream) {
        return solve(init(stream), false);
    }

    @Override
    public long part2(Stream<String> stream) {
        return solve(init(stream), true);
    }

    public Map<String, List<String>> init(Stream<String> stream) {
        final Map<String, List<String>> graph = new HashMap<>();
        stream.map(line -> line.split("-"))
                .forEach(split -> {
                    graph.computeIfAbsent(split[0], k -> new ArrayList<>()).add(split[1]);
                    graph.computeIfAbsent(split[1], k -> new ArrayList<>()).add(split[0]);
                });
        solve(graph, true);
        return graph;
    }

    private long solve(Map<String, List<String>> graph, boolean isPart2) {
        Deque<Node> queue = new LinkedList<>();
        queue.push(new Node(START_NODE, "", null));

        long counter = 0;
        while (!queue.isEmpty()) {
            Node currentPath = queue.pop();

            for (String current : graph.get(currentPath.currentPos)) {
                if (current.equals(END_NODE)) {
                    counter++;//Found a Way to the end
                    continue;
                }

                if (current.equals(START_NODE)) {
                    continue;//don't go back to the start
                }

                boolean isSmallCave = current.toLowerCase().equals(current);
                if (isSmallCave) {
                    if (!currentPath.path.contains(current)) { // not visited yet
                        queue.add(new Node(current, currentPath.path + current, currentPath.smallCave));
                    } else if (isPart2 && currentPath.smallCave == null) {
                        queue.add(new Node(current, currentPath.path + current, current));
                    }
                } else {
                    queue.add(new Node(current, currentPath.path + current, currentPath.smallCave));
                }
            }
        }
        return counter;
    }

    private record Node(String currentPos, String path, String smallCave) {
    }

}
