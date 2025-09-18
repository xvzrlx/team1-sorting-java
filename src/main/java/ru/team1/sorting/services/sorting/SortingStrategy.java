package ru.team1.sorting.services.sorting;

public interface SortingStrategy<T> {
    int compare(T a, T b);
}
