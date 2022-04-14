package producerConsumer;

record IterationResult<T>(T data, boolean hasMore) {
    IterationResult(T data) {
        this(data, true);
    }
}
