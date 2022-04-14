package cs121;

import java.util.function.Function;

public final class WordFrequencyParser implements Function<String[], WordFrequency> {

    private static WordFrequencyParser instance = null;

    private WordFrequencyParser() {}

    public static WordFrequencyParser getInstance() {
        if (instance == null) {
            instance = new WordFrequencyParser();
        }
        return instance;
    }

    @Override
    public WordFrequency apply(String[] strings) {
        String word = strings[0].trim();
        long freq = Long.parseLong(strings[2].trim());
        return new WordFrequency(word, freq);
    }
}
