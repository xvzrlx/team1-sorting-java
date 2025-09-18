package ru.team1.sorting.services.search;

import org.junit.Test;
import ru.team1.sorting.model.Book;
import ru.team1.sorting.services.sorting.SortByPages;
import ru.team1.sorting.services.sorting.SortByTitle;
import ru.team1.sorting.services.sorting.SortByYear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearchTest {
    List<Book> books = new ArrayList<>(Arrays.asList(
            new Book.Builder().title("Война и мир").pages(800).year(1869).build(),
            new Book.Builder().title("Маленький принц").pages(128).year(1943).build(),
            new Book.Builder().title("Гари Поттер и философский камень").pages(384).year(1997).build()
    ));

    @Test
    public void testSearchByTitle() {
        var object = BinarySearch.search(books, "маленький принц", new SortByTitle<>(), new SearchByTitle<>());
    }
    @Test
    public void testSearchByYear() {
        var object = BinarySearch.search(books, 1869, new SortByYear<>(), new SearchByYear<>());
    }
    @Test
    public void testSearchByPages() {
        var object = BinarySearch.search(books, 384, new SortByPages<>(), new SearchByPages<>());
    }
}
