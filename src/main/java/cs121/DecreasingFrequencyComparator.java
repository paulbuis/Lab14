package cs121;

import java.util.Comparator;


public final class DecreasingFrequencyComparator implements Comparator<WordFrequency> {
    private static DecreasingFrequencyComparator instance = null;

    public static DecreasingFrequencyComparator getInstance() {
        if (instance == null) {
            instance = new DecreasingFrequencyComparator();
        }
        return instance;
    }

    private DecreasingFrequencyComparator() {}

    @Override
    public int compare(WordFrequency wf1, WordFrequency wf2) {
        Long freq1 = wf1.getFrequency();
        Long freq2 = wf2.getFrequency();

        return -freq1.compareTo(freq2);
    }
}
