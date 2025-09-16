package ru.team1.sorting.services.search;

import ru.team1.sorting.model.HasYear;

public class SearchByYear<T extends HasYear> implements SearchStrategy<T, Integer> {
    @Override
    public int compareWithKey(T obj, Integer key) {
        return Integer.compare(obj.getYear(), key);
    }
}
