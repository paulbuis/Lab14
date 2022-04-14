package app;

import java.util.List;
import java.util.Comparator;
import java.util.Map;

import java.util.stream.Stream;

import cs121.*;

public class App {

    static Comparator<WordFrequency> decreasingFrequency =
            DecreasingFrequencyComparator.getInstance();

    public static void main(String[] args) {

        WordFrequencyGenerator wfGen = new WordFrequencyGenerator();
        Stream<WordFrequency> parsed = wfGen.stream();

        // For Lab on 4/14/2022, use this chunk of code
        List<WordFrequency> wfList = parsed.toList();
        for (WordFrequency wf: wfList){
            String word = wf.getWord();
            long freq = wf.getFrequency();
            System.out.printf("%s: %d\n", word, freq);
        }

        // We hava a List<WordFrequency> with duplicate words!!!


        // for Project 3A remove the chunk of code
        // immediately above this comment
        // and uncomment the chunk of code below:

        /*
        Map<String, List<WordFrequency>> alphaMap = Grouper.group(parsed);

        // declare an empty list here!!
        for (String word : alphaMap.keySet()) {
            List<WordFrequency> wfList = alphaMap.get(word);
            Long freqSum = wfList.stream().
                    map(wf->wf.getFrequency()).
                    reduce(0L, (f1, f2)->f1+f2);
            WordFrequency wf = new WordFrequency(word, freqSum);
            // instead of printing, add to the list
            System.out.printf("%s: %d\n", wf.getWord(), wf.getFrequency());
        }

        // now sort the list using List.sort(decreasingFrequency)

         */
    }
}
