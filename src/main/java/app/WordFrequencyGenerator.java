package app;

import cs121.IsInteresting;
import cs121.Splitter;
import cs121.WordFrequencyParser;
import cs121.WordFrequency;

import producerConsumer.Generator;
import producerConsumer.ScannerGenerator;

import java.io.File;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public final class WordFrequencyGenerator
        extends Generator<WordFrequency> {

    static Function<String, String[]> splitter =
            Splitter.getInstance();

    static Predicate<String[]> isInteresting =
            IsInteresting.getInstance();

    static Function<String[], WordFrequency> parser =
            WordFrequencyParser.getInstance();

    private final ScannerGenerator lineGenerator =
            new ScannerGenerator(
            new File("a.txt")
    );


    @Override
    public void produce(Consumer<WordFrequency> consumer) {
        Stream<String> lines =
                lineGenerator.stream();

        Stream<String[]> splits =
                lines.map(splitter);

        Stream<String[]> interesting =
                splits.filter(isInteresting);

        Stream<WordFrequency> parsed =
                interesting.map(parser);

        parsed.forEach(consumer);
    }
}
