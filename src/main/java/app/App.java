package app;

import java.util.List;
import java.util.Comparator;

import java.util.stream.Stream;

import cs121.*;

public class App {

    static Comparator<WordFrequency> decreasingFrequency =
            DecreasingFrequencyComparator.getInstance();

    public static void main(String[] args) {

        GroupingGenerator wfGen = new GroupingGenerator();
        Stream<WordFrequency> grouped = wfGen.stream();
        Stream<WordFrequency> sorted = grouped.sorted(DecreasingFrequencyComparator.getInstance());
        // For Lab on 4/14/2022, use this chunk of code
        List<WordFrequency> wfList = sorted.toList();
        for (WordFrequency wf: wfList){
            String word = wf.getWord();
            long freq = wf.getFrequency();
            System.out.printf("%s: %d\n", word, freq);
        }

        // We have a List<WordFrequency> without duplicate words!!!


        // for Project 3A remove the chunk of code
        // immediately above this comment

        // now sort the list using List.sort(decreasingFrequency)

    }
}
