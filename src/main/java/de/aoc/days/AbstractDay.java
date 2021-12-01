package de.aoc.days;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public abstract class AbstractDay implements Day {

    @Override
    public Stream<String> getDebugInput() {
        return getInput("debug.txt");
    }

    @Override
    public Stream<String> getActualInput() {
        return getInput("input.txt");
    }

    @Override
    public long part1() {
        try (Stream<String> toClose = getActualInput()) {
            return part1(toClose);
        }
    }

    @Override
    public long part2() {
        try (Stream<String> toClose = getActualInput()) {
            return part2(toClose);
        }
    }

    protected Stream<String> getInput(final String name) {
        try {
            final URI toSrc = getClass().getResource(name).toURI();
            return Files.lines(Path.of(toSrc));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }
}
