package ru.team1.sorting.services.sorting;

import ru.team1.sorting.model.HasYear;

public class SortEvenYear<T extends HasYear> extends SortByYear<T> implements EvenStrategy<T>{
    @Override
    public boolean checkEven(T a) {
        return (a.getYear() & 1) == 0;
    }
}
