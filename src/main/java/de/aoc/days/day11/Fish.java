package de.aoc.days.day11;

import java.util.Objects;

class Fish {
    private int level;
    private boolean isFlashing;

    Fish(int level) {
        this.level = level;
        this.isFlashing = false;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isFlashing() {
        return isFlashing;
    }

    public void setFlashing(boolean flashing) {
        isFlashing = flashing;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "level=" + level +
                ", isFlashing=" + isFlashing +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fish fish)) return false;
        return level == fish.level && isFlashing == fish.isFlashing;
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, isFlashing);
    }
}
