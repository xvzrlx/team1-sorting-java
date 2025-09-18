package ru.team1.sorting.services.sorting;

import ru.team1.sorting.model.HasPage;

public class SortByPages<T extends HasPage> implements SortingStrategy<T> {
    @Override
    public int compare(T a, T b) {
        return Integer.compare(a.getPages(), b.getPages());
    }
}
