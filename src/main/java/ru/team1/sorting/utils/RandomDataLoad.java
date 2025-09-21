package ru.team1.sorting.utils;

import ru.team1.sorting.model.Book;

import java.util.Random;

public class RandomDataLoad {
    CustomArrayList<Book> books = new CustomArrayList<>();

       public void addBooks() {
           books.add(new Book.Builder().title("Война и мир").year(1869).pages(1225).build());
           books.add(new Book.Builder().title("Преступление и наказание").year(1866).pages(671).build());
           books.add(new Book.Builder().title("Мастер и Маргарита").year(1967).pages(480).build());
           books.add(new Book.Builder().title("Анна Каренина").year(1877).pages(864).build());
           books.add(new Book.Builder().title("Идиот").year(1869).pages(656).build());
           books.add(new Book.Builder().title("Дубровский").year(1841).pages(224).build());
           books.add(new Book.Builder().title("Евгений Онегин").year(1833).pages(416).build());
           books.add(new Book.Builder().title("Доктор Живаго").year(1957).pages(592).build());
           books.add(new Book.Builder().title("Белая гвардия").year(1925).pages(352).build());
           books.add(new Book.Builder().title("Собачье сердце").year(1925).pages(128).build());
           books.add(new Book.Builder().title("Тихий Дон").year(1940).pages(1600).build());
           books.add(new Book.Builder().title("Отцы и дети").year(1862).pages(336).build());
           books.add(new Book.Builder().title("Мёртвые души").year(1842).pages(352).build());
           books.add(new Book.Builder().title("Ревизор").year(1836).pages(192).build());
           books.add(new Book.Builder().title("Герой нашего времени").year(1840).pages(304).build());
           books.add(new Book.Builder().title("Капитанская дочка").year(1836).pages(256).build());
           books.add(new Book.Builder().title("Хаджи-Мурат").year(1912).pages(192).build());
           books.add(new Book.Builder().title("Обломов").year(1859).pages(608).build());
           books.add(new Book.Builder().title("Тарас Бульба").year(1835).pages(240).build());
           books.add(new Book.Builder().title("Чевенгур").year(1928).pages(640).build());
       }

       public CustomArrayList<Book> getRandomBooks() {
           Random random = new Random();
           return random.ints(0, books.size())
                   .distinct()
                   .limit(10)
                   .mapToObj(books::get)
                   .collect(CustomArrayList.toCustomArrayList());
       }
}
