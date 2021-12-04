package de.aoc.days.day4;

class Number {
    public final int number;
    public boolean isMarked;

    Number(int number) {
        this.number = number;
        this.isMarked = false;
    }

    Number mark() {
        this.isMarked = true;
        return this;
    }

    boolean isMarked() {
        return this.isMarked;
    }

    int getNumber() {
        return this.number;
    }
}
