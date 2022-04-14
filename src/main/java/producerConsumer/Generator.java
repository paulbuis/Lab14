package producerConsumer;

import java.io.Closeable;
import java.util.Iterator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class Generator<T> implements Iterable<T>, Closeable {


    private final Channel<T> channel;

    public Generator() {
        this(0);
    }

    public Generator(int channelCapacity) {
        channel = new Channel<>(channelCapacity);
    }

    @Override
    public final Iterator<T> iterator() {
        Thread thread = new Thread(new Runner());
        thread.start();
        return channel.iterator();
    }

    public final Stream<T> stream() {
        Thread thread = new Thread(new Runner());
        thread.start();
        return channel.stream();
    }

    public final Stream<T> filter(Predicate<T> predicate) {
        return stream().filter(predicate);
    }

    public final <R> Stream<R> map(Function<T,R> transform) {
        return stream().map(transform);
    }

    public final T reduce(T identity, BinaryOperator<T> combiner){
        return stream().reduce(identity, combiner);
    }

    @Override
    public final void close() {
        channel.close();
    }

    public abstract void produce(Consumer<T> consumer);

    private final class Runner implements Runnable {
        @Override
        public void run() {
            if (Generator.this.channel.closed) {
                System.err.println("Generator channel already closed");
                return;
            }
            try {
                produce(new ChannelConsumer());
            } catch (RuntimeException rex) {
                rex.printStackTrace();
            }
            close();
        }
    }

    private final class ChannelConsumer implements Consumer<T> {

        @Override
        public void accept(T item) {
            channel.send(item);
        }
    }
}