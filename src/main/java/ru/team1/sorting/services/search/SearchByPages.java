package ru.team1.sorting.services.search;

import ru.team1.sorting.model.HasPage;

public class SearchByPages<T extends HasPage> implements SearchStrategy<T, Integer> {
    @Override
    public int compareWithKey(T obj, Integer key) {
        return Integer.compare(obj.getPages(), key);
    }
}
