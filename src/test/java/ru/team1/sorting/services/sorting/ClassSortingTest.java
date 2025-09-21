package ru.team1.sorting.services.sorting;

import org.junit.jupiter.api.Test;
import ru.team1.sorting.model.HasPage;
import ru.team1.sorting.model.HasTitle;
import ru.team1.sorting.model.HasYear;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassSortingTest {
    static class Book implements HasTitle, HasPage, HasYear {
        private String title;
        private int page;
        private int year;
        public Book(String title, int page, int year) {
            this.title = title;
            this.page = page;
            this.year = year;
        }

        @Override
        public String getTitle() {
            return title;
        }
        @Override
        public int getPages() {
            return page;
        }
        @Override
        public int getYear() {
            return year;
        }
    }

    List<Book> books = new ArrayList<>(Arrays.asList(
            new Book("Война и мир", 800, 1869),
            new Book("Маленький принц", 128, 1943),
            new Book("Гари Поттер и философский камень", 384, 1997)
    ));

    @Test
    public void testSortingByTitle() {
        ClassSorting.sort(books, new SortByTitle<>());
    }
    @Test
    public void testSortingByPage() {
        ClassSorting.sort(books, new SortByPage<>());
    }
    @Test
    public void testSortingByYear() {
        ClassSorting.sort(books, new SortByYear<>());
    }
}
