package de.aoc.days.day4;

import de.aoc.days.AbstractDay;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day4 extends AbstractDay {
    @Override
    public long part1(Stream<String> stream) {
        List<String> input = stream.collect(Collectors.toList());
        final List<Integer> numbers = Arrays.stream(input.remove(0).split(","))
                .map(Integer::parseInt)
                .toList();
        input.remove(0);//empty line after drawn numbers

        final List<List<List<Number>>> cards = getCards(input);
        return calcWinner(runNumbers(numbers, cards));
    }

    @Override
    public long part2(Stream<String> stream) {
        List<String> input = stream.collect(Collectors.toList());
        final List<Integer> numbers = Arrays.stream(input.remove(0).split(","))
                .map(Integer::parseInt)
                .toList();
        input.remove(0);//empty line after drawn numbers

        final List<List<List<Number>>> cards = getCards(input);
        return calcWinner(runNumbers2(numbers, cards));
    }

    private long calcWinner(Winner winner) {
        int sum = winner.winningCard.stream()
                .flatMap(Collection::stream)
                .filter(Predicate.not(Number::isMarked))
                .mapToInt(Number::getNumber)
                .sum();
        return (long) sum * winner.lastNumber();
    }

    private Winner runNumbers(List<Integer> numbers, List<List<List<Number>>> cards) {
        for (final int drawnNumber : numbers) {
            for (final List<List<Number>> card : cards) {
                Optional<Winner> maybeWinner = card.stream()
                        .flatMap(Collection::stream)
                        .filter(n -> n.number == drawnNumber)
                        .findFirst()
                        .map(Number::mark)
                        .map(n -> checkForWinner(card, n))
                        .map(win -> new Winner(win, drawnNumber));

                if (maybeWinner.isPresent()) {
                    return maybeWinner.orElseThrow();
                }
            }
        }
        return null;
    }


    private Winner runNumbers2(List<Integer> numbers, List<List<List<Number>>> cards) {
        for (final int drawnNumber : numbers) {
            Iterator<List<List<Number>>> it = cards.iterator();
            while (it.hasNext()) {
                List<List<Number>> current = it.next();
                Optional<Winner> maybeWinner = current
                        .stream()
                        .flatMap(Collection::stream)
                        .filter(n -> n.number == drawnNumber)
                        .findFirst()
                        .map(Number::mark)
                        .map(n -> checkForWinner(current, n))
                        .map(win -> new Winner(win, drawnNumber));

                if (maybeWinner.isPresent()) {
                    if (cards.size() > 1) {
                        it.remove();
                    } else {
                        return maybeWinner.orElseThrow();
                    }
                }
            }
        }
        return null;
    }

    private List<List<Number>> checkForWinner(List<List<Number>> card, Number n) {
        List<Number> rowWithNumber = card.stream()
                .filter(row -> row.contains(n))
                .findFirst()
                .orElseThrow();
        final boolean isWinningRow = rowWithNumber.stream().allMatch(Number::isMarked);
        if (isWinningRow) {
            return card;
        }

        final int index = rowWithNumber.indexOf(n);
        final boolean isWinningColumn = card.stream()
                .map(row -> row.get(index))
                .allMatch(Number::isMarked);
        if (isWinningColumn) {
            return card;
        }


        return null;
    }

    private List<List<List<Number>>> getCards(List<String> input) {
        final List<List<List<Number>>> cards = new ArrayList<>();
        List<List<Number>> currentCard = new ArrayList<>();
        for (final String line : input) {
            if (line.isBlank()) {
                cards.add(currentCard);
                currentCard = new ArrayList<>();
                continue;
            }

            currentCard.add(Arrays.stream(line.split("\\s")).filter(Predicate.not(String::isBlank)).map(Integer::parseInt).map(Number::new).toList());
        }
        cards.add(currentCard);
        return cards;
    }

    private record Winner(List<List<Number>> winningCard, Integer lastNumber) {

    }

    private static class Number {
        public final int number;
        public boolean isMarked;

        private Number(int number) {
            this.number = number;
            this.isMarked = false;
        }

        public Number mark() {
            this.isMarked = true;
            return this;
        }

        public boolean isMarked() {
            return this.isMarked;
        }

        public int getNumber() {
            return this.number;
        }
    }

}


