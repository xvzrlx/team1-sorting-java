package ru.team1.sorting.services.sorting;

import junit.framework.TestCase;
import ru.team1.sorting.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EvenOnlySortingTest extends TestCase {

    List<Book> books = new ArrayList<>(Arrays.asList(
            new Book.Builder().title("Война и мир").pages(800).year(1869).build(),
            new Book.Builder().title("Маленький принц").pages(1217).year(1943).build(),
            new Book.Builder().title("Гари Поттер и философский камень").pages(384).year(1998).build(),
            new Book.Builder().title("Война и мир ч4").pages(8003).year(1869).build(),
            new Book.Builder().title("Маленький принц ч4").pages(128).year(1943).build(),
            new Book.Builder().title("Гари Поттер и философский камень ч4").pages(384).year(1996).build(),
            new Book.Builder().title("Война и мир ч3").pages(800).year(1869).build(),
            new Book.Builder().title("Маленький принц ч3").pages(128).year(1940).build(),
            new Book.Builder().title("Гари Поттер и философский камень ч3").pages(384).year(1995).build(),
            new Book.Builder().title("Война и мир ч2").pages(8001).year(1860).build(),
            new Book.Builder().title("Маленький принц ч2").pages(128).year(1943).build(),
            new Book.Builder().title("Гари Поттер и философский камень ч2").pages(3841).year(1994).build()
    ));

    List<Book> booksEven = new ArrayList<>(Arrays.asList(
            new Book.Builder().title("Война и мир").pages(800).year(1860).build(),
            new Book.Builder().title("Маленький принц").pages(1217).year(1940).build(),
            new Book.Builder().title("Гари Поттер и философский камень").pages(384).year(1998).build(),
            new Book.Builder().title("Война и мир ч4").pages(8003).year(1850).build(),
            new Book.Builder().title("Маленький принц ч4").pages(128).year(1944).build(),
            new Book.Builder().title("Гари Поттер и философский камень ч4").pages(384).year(1996).build()
    ));

    List<Book> booksNoEven = new ArrayList<>(Arrays.asList(
            new Book.Builder().title("Война и мир").pages(800).year(1861).build(),
            new Book.Builder().title("Маленький принц").pages(1217).year(1941).build(),
            new Book.Builder().title("Гари Поттер и философский камень").pages(384).year(1991).build(),
            new Book.Builder().title("Война и мир ч4").pages(8003).year(1853).build(),
            new Book.Builder().title("Маленький принц ч4").pages(128).year(1943).build(),
            new Book.Builder().title("Гари Поттер и философский камень ч4").pages(384).year(1993).build()
    ));

    public void testSortYear() {
        EvenOnlySorting.sort(books, new SortEvenYear<>());
    }

    public void testSortPages() {
        EvenOnlySorting.sort(books, new SortEvenPages<>());
    }

    //Only even numbers present in collection
    public void testSortYearEven() {
        EvenOnlySorting.sort(booksEven, new SortEvenYear<>());
    }

    //No even numbers present in collection
    public void testSortYearNoEven() {
        EvenOnlySorting.sort(booksNoEven, new SortEvenYear<>());
    }
}