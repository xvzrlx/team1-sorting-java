package ru.team1.sorting.services.sorting;

import ru.team1.sorting.model.HasYear;

public class SortByYear<T extends HasYear> implements SortingStrategy<T> {
    @Override
    public int compare(T a, T b) {
        return Integer.compare(a.getYear(), b.getYear());
    }
}
