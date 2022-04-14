package cs121;


public final class WordFrequency implements Comparable<WordFrequency>{
    private final String word;
    private final long frequency;

    public WordFrequency(String word, long frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public long getFrequency() {
        return frequency;
    }

    @Override
    public int compareTo(WordFrequency otherWF) {
        if (frequency == otherWF.frequency) {
            return 0;
        }

        if (frequency < otherWF.frequency) {
            return 1;
        }

        return -1;
    }

    @Override
    public String toString() {
        return word + "\t" + frequency;
    }
}
