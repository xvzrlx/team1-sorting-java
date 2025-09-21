package ru.team1.sorting.services.sorting;

import org.junit.Test;
import ru.team1.sorting.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassSortingTest {
    List<Book> books = new ArrayList<>(Arrays.asList(
            new Book.Builder().title("Война и мир").pages(800).year(1869).build(),
            new Book.Builder().title("Маленький принц").pages(128).year(1943).build(),
            new Book.Builder().title("Гари Поттер и философский камень").pages(384).year(1997).build()
    ));

    @Test
    public void testSortingByTitle() {
        ClassSorting.sort(books, new SortByTitle<>());
    }
    @Test
    public void testSortingByPages() {
        ClassSorting.sort(books, new SortByPages<>());
    }
    @Test
    public void testSortingByYear() {
        ClassSorting.sort(books, new SortByYear<>());
    }
}
