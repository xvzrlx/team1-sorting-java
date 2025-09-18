package ru.team1.sorting.services.sorting;

import ru.team1.sorting.model.HasTitle;

public class SortByTitle<T extends HasTitle> implements SortingStrategy<T> {
    @Override
    public int compare(T a, T b) {
        return a.getTitle().compareTo(b.getTitle());
    }
}
