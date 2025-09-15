package ru.team1.sorting.services.sorting;

import ru.team1.sorting.model.HasPage;

public class SortByPage<T extends HasPage> implements SortingStrategy<T> {
    @Override
    public int compare(T a, T b) {
        return Integer.compare(a.getPage(), b.getPage());
    }
}
