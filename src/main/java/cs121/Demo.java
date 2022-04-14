package cs121;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Demo {
    static class WordFreq{
        String word;
        long freq;
        public WordFreq(String word, long freq) {
            this.word = word;
            this.freq = freq;
        }
        public String toString() {
            return "(" + word + ", " + freq + ")";
        }
    }
    static Function<WordFreq, String> extractSecondaryKey = wf->wf.word;
    static Function<WordFreq, Long> extractPrimaryKey = wf->wf.freq;
    static Comparator<String> secondaryKeyComparator = Comparator.naturalOrder();
    static Comparator<Long> primaryKeyComparator = new Comparator<>() {
        static final Comparator<Long> naturalLong = Comparator.naturalOrder();
        public int compare(Long a, Long b) {
            return -naturalLong.compare(a, b);
        }
    };

    public static void main(String[] args) {
        List<WordFreq> wfList = List.of(
                new WordFreq("how", 1),
                new WordFreq("much", 1),
                new WordFreq("wood", 2),
                new WordFreq("would", 1),
                new WordFreq("woodchuck", 2),
                new WordFreq("chuck", 2),
                new WordFreq("if", 1),
                new WordFreq("could", 1));
        MultiSort<WordFreq, Long, String> comp = new MultiSort<>(
                extractPrimaryKey, extractSecondaryKey,
                primaryKeyComparator, secondaryKeyComparator);

        List<WordFreq> list1 = new ArrayList<>(wfList);
        System.out.println(list1);
        list1.sort(comp.getSecondaryComparator());
        System.out.println(list1);
        list1.sort(comp.getPrimaryComparator());
        System.out.println(list1);

        System.out.println("====");

        List<WordFreq> list3 = comp.sort(new ArrayList<>(wfList));
        System.out.println(list3);

        System.out.println("====");

        List<WordFreq> list2 = new ArrayList<>(wfList);
        list2.sort(comp);
        System.out.println(list2);


    }
}