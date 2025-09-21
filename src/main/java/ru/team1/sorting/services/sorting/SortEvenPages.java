package ru.team1.sorting.services.sorting;

import ru.team1.sorting.model.HasPage;

public class SortEvenPages<T extends HasPage> extends SortByPages<T> implements EvenStrategy<T> {
    @Override
    public boolean checkEven(T a) {
        return (a.getPages() & 1) == 0;
    }
}
