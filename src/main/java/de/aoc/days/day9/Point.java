package de.aoc.days.day9;

import java.util.Objects;

public record Point(int y, int x, int dangerLevel) {

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point other)) {
            return false;
        }
        return this.y == other.y &&
                this.x == other.x &&
                this.dangerLevel == other.dangerLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x, this.dangerLevel);
    }

}
