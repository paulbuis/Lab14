package cs121;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MultiSort<T, K1, K2> implements Comparator<T> {
    private final Function<T, K1> extractPrimaryKey;
    private final Function<T, K2> extractSecondaryKey;
    private final Comparator<? super K1> primaryKeyComparator;
    private final Comparator<? super K2> secondaryKeyComparator;

    private final Comparator<T> primaryComparator = new Comparator<>() {
        public int compare(T a, T b) {
            K1 aKey = extractPrimaryKey.apply(a);
            K1 bKey = extractPrimaryKey.apply(b);
            return primaryKeyComparator.compare(aKey, bKey);
        }
    };

    private final Comparator<T> secondaryComparator = new Comparator<>() {
        public int compare(T a, T b) {
            K2 aKey = extractSecondaryKey.apply(a);
            K2 bKey = extractSecondaryKey.apply(b);
            return secondaryKeyComparator.compare(aKey, bKey);
        }
    };

    public MultiSort(Function<T, K1> extractPrimaryKey,
        Function<T, K2> extractSecondaryKey,
        Comparator<K1> primaryKeyComparator,
        Comparator<K2> secondaryKeyComparator) {
        this.extractPrimaryKey = extractPrimaryKey;
        this.extractSecondaryKey = extractSecondaryKey;
        this.primaryKeyComparator = primaryKeyComparator;
        this.secondaryKeyComparator = secondaryKeyComparator;
    }

    public Comparator<T> getPrimaryComparator() {
        return primaryComparator;
    }

    public Comparator<T> getSecondaryComparator() {
        return secondaryComparator;
    }

    public int compare(T a, T b) {
        int result = primaryComparator.compare(a, b);
        if (result == 0) {
            return secondaryComparator.compare(a, b);
        }
        return result;
    }


    public List<T> sort(List<T> inputList) {
        final int inputSize = inputList.size();
        final Map<K1, List<T>> groupMap = inputList.stream().collect(
                Collectors.groupingBy(extractPrimaryKey));
        final SortedMap<K1, List<T>> sortedMap = new ConcurrentSkipListMap<>(primaryKeyComparator);

        groupMap.entrySet().stream()
                .forEach(mapEntry-> {
                            List<T> subList = mapEntry.getValue();
                            subList.sort(secondaryComparator);
                            sortedMap.put(mapEntry.getKey(), subList);
                        }
                );

        final List<T> result = new ArrayList<>(inputSize);
        // sequentially append each sublist into the result list
        for (K1 key: sortedMap.keySet()) {
            List<T> subList = sortedMap.get(key);
            result.addAll(subList);
        }
        return result;
    }
}