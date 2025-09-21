package ru.team1.sorting.services.sorting;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SortType {
    BY_TITLE(new SortByTitle<>()), BY_YEAR(new SortByYear()), BY_PAGES(new SortByPages());

    final SortingStrategy sortingStrategy;

}
