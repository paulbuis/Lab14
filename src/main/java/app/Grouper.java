package app;

import java.util.SortedMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.*;


import cs121.DictionaryWordComparator;
import cs121.WordFrequency;

public class Grouper {

    public static SortedMap<String, List<WordFrequency>> group(Stream<WordFrequency> wfStream) {
        return wfStream.collect(Collectors.groupingBy(
                wf -> wf.getWord(),
                ()-> new TreeMap<>(DictionaryWordComparator.getInstance()),
                Collectors.toList()));
    }
}
