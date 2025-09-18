package ru.team1.sorting.services.search;

public interface SearchStrategy<T, K> {
    int compareWithKey(T obj, K key);
}
