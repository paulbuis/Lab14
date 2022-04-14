package cs121;

import java.util.function.Predicate;

public final class IsInteresting implements Predicate<String[]> {

    private static IsInteresting instance = null;

    public static IsInteresting getInstance() {
        if (instance == null) {
            instance = new IsInteresting();
        }
        return instance;
    }

    private IsInteresting() {} // prevents anyone else from creating one
                               // standard part of lazy construction
                               // of a Singleton object

    static public boolean has4(String[] array) {
        return array.length >= 4;
    }
    static public final Predicate<String[]> has4 =
            IsInteresting::has4;

    static public boolean noneBlank(String[] array) {
        return !(array[0].isBlank() || array[1].isBlank() ||
                array[2].isBlank() || array[3].isBlank());
    }
    static public final Predicate<String[]> noneBlank =
            IsInteresting::noneBlank;

    static public boolean isYear2000orLater(String[] array) {
        return array[1].compareTo("2000") >= 0;
    }
    static public final Predicate<String[]> isYear2000orLater =
            IsInteresting::isYear2000orLater;

    public static boolean wordAllLetters(String[] array) {
        String word = array[0];
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
    public static final Predicate<String[]> wordAllLetters =
            IsInteresting::wordAllLetters;


    @Override
    public boolean test(String[] strings) {
        return has4(strings) &&
                noneBlank(strings) &&
                isYear2000orLater(strings) &&
                wordAllLetters(strings);
    }
}
