package ru.team1.sorting.services.sorting;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.team1.sorting.model.Book;

@RequiredArgsConstructor
@Getter
public enum SortType {
    BY_TITLE(new SortByTitle<Book>()), BY_YEAR(new SortByYear<Book>()), BY_PAGES(new SortByPages<Book>());

    final SortingStrategy<Book> sortingStrategy;

}
