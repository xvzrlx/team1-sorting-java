package ru.team1.sorting.utils;

import ru.team1.sorting.model.Book;
import ru.team1.sorting.utils.exceptions.ParseBookException;

public abstract class DataLoad {
    Book parseBook(String line) {
        // формат: "Название,страницы,год"

        String[] parts = line.split(",");
        if (parts.length != 3) {
            throw new ParseBookException("Неверный формат: ожидалось 3 поля, разделённых запятой");
        }

        String title = parts[0].trim();
        int pages;
        int year;

        try {
            pages = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new ParseBookException("Неверный формат числа страниц: " + parts[1]);
        }

        try {
            year = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            throw new ParseBookException("Неверный формат года: " + parts[2]);
        }

        return new Book.Builder()
                .title(title)
                .pages(pages)
                .year(year)
                .build();
    }
}
