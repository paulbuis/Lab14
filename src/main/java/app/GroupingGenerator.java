package app;

import java.util.SortedMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.*;


import cs121.DictionaryWordComparator;
import cs121.WordFrequency;
import producerConsumer.Generator;

public class GroupingGenerator extends Generator<WordFrequency> {

    public GroupingGenerator() {
        super(1000);
    }

    public static SortedMap<String, List<WordFrequency>> group(Stream<WordFrequency> wfStream) {
        return wfStream.collect(Collectors.groupingBy(
                WordFrequency::getWord,
                ()-> new TreeMap<>(DictionaryWordComparator.getInstance()),
                Collectors.toList()));
    }

    @Override
    public void produce(Consumer<WordFrequency> consumer) {
        WordFrequencyGenerator wfGen = new WordFrequencyGenerator();
        Stream<WordFrequency> parsed = wfGen.stream();
        Map<String, List<WordFrequency>> alphaMap = group(parsed);
        for (String word : alphaMap.keySet()) {
            List<WordFrequency> wfList = alphaMap.get(word);
            Long freqSum = wfList.stream().
                    map(WordFrequency::getFrequency).
                    reduce(0L, Long::sum);
            WordFrequency wf = new WordFrequency(word, freqSum);
            consumer.accept(wf);
        }
    }
}
