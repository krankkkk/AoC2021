package de.aoc.days.day9;

import java.util.Set;

record Basin(Set<Point> points) implements Comparable<Basin> {

    @Override
    public int compareTo(Basin o) {
        return Integer.compare(o.points.size(), this.points.size());
    }

    @Override
    public String toString() {
        return "Basin{size=%d}".formatted(this.points.size());
    }
}
