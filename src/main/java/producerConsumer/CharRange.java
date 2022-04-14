package producerConsumer;

import java.util.function.Consumer;

public class CharRange extends Generator<Character> {
    private final char maxRange;
    private final char minRange;

    public CharRange(char minRange, char maxRange) {
        if (minRange <= maxRange) {
            this.minRange = minRange;
            this.maxRange = maxRange;
        } else {
            this.maxRange = minRange;
            this.minRange = maxRange;
        }
    }

    @Override
    public void produce(Consumer<Character> consumer) {
        for(char ch=minRange; ch<=maxRange; ch++) {
            consumer.accept(ch);
        }
    }
}
