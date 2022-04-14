package cs121;

import java.util.function.Function;

public class Splitter implements Function<String, String[]> {
    private static Splitter instance = null;

    public static Splitter getInstance() {
        if (instance == null) {
            instance = new Splitter();
        }
        return instance;
    }

    private Splitter() {} // prevents default/no-arg constructor
                          // from being called elsewhere.

    @Override
    public String[] apply(String s) {
        return s.split("\t");
    }
}
