package ru.team1.sorting.services.search;

import ru.team1.sorting.model.HasTitle;
import ru.team1.sorting.services.sorting.SortingStrategy;

public class SearchByTitle<T extends HasTitle> implements SearchStrategy<T, String> {
    @Override
    public int compareWithKey(T obj, String key) {
        return obj.getTitle().compareToIgnoreCase(key);
    }
}
