package ru.team1.sorting.services.sorting;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.team1.sorting.model.Book;

@RequiredArgsConstructor
@Getter
public enum SortType {
    BY_TITLE(new SortByTitle<Book>(), new SortEvenPages<>()), BY_YEAR(new SortByYear<Book>(), new SortEvenYear<>()), BY_PAGES(new SortByPages<Book>(), new SortEvenPages<>());

    final SortingStrategy<Book> sortingStrategy;
    final EvenStrategy<Book> evenStrategy;

}
