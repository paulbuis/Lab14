package producerConsumer;

import java.util.function.Consumer;

public final class Range extends Generator<Integer> {
    private final int maxRange;
    private final int minRange;

    public Range(int maxRange) {
        this(0, maxRange);
    }

    public Range(int minRange, int maxRange) {
        if (minRange <= maxRange) {
            this.minRange = minRange;
            this.maxRange = maxRange;
        } else {
            this.maxRange = minRange;
            this.minRange = maxRange;
        }
    }

    @Override
    public void produce(Consumer<Integer> consumer) {
        for(int i=minRange; i<maxRange; i++) {
            consumer.accept(i);
        }
    }
}