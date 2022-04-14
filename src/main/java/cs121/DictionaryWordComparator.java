package cs121;

import java.util.Comparator;

public final class DictionaryWordComparator implements Comparator<String> {
    private static DictionaryWordComparator instance = null;

    public static DictionaryWordComparator getInstance() {
        if (instance == null) {
            instance = new DictionaryWordComparator();
        }
        return instance;
    }

    private DictionaryWordComparator() {}

    private static final Comparator<String> natural =
            Comparator.naturalOrder();

    private static final Comparator<String> caseInsensitive =
            String.CASE_INSENSITIVE_ORDER;


    public int compare(String word1, String word2) {
        int result = caseInsensitive.compare(word1, word2);
        if (result == 0) {
            return natural.compare(word1, word2);
        }
        return result;
    }
}
