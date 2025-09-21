package ru.team1.sorting.services.sorting;

public interface EvenStrategy<T> extends SortingStrategy<T> {
    boolean checkEven(T a);
}
