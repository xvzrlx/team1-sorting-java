package ru.team1.sorting.utils;
import ru.team1.sorting.model.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDataLoad {
    public List<Book> loadFromFile(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Путь к файлу не может быть пустым");
        }
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) continue;

                try {
                    Book book = parseBook(line);
                    books.add(book);
                } catch (Exception e) {
                    throw new RuntimeException("Ошибка в строке " + lineNumber + ": " + e.getMessage(), e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать файл: " + filePath, e);
        }
        System.out.println("✅ Загружено " + books.size() + " книг из файла.");
        return books;
    }

    private Book parseBook(String line) {
        // формат: "Название,страницы,год"

        String[] parts = line.split(",");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат: ожидалось 3 поля, разделённых запятой");
        }

        String title = parts[0].trim();
        int pages;
        int year;

        try {
            pages = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат числа страниц: " + parts[1]);
        }

        try {
            year = Integer.parseInt(parts[2].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный формат года: " + parts[2]);
        }

        return new Book.Builder()
                .title(title)
                .pages(pages)
                .year(year)
                .build();
    }

    public CustomArrayList<Book> loadFromFileByStream(String filePath) throws IOException {
        if (filePath == null || filePath.isEmpty()) throw new RuntimeException("Путь к файлу не может быть пустым!");
        try (Stream<String> lines = Files.lines(Path.of(filePath))) {
            return lines
                    .filter(line -> !line.isEmpty())
                    .map(line -> line.split(","))
                    .filter(parts -> parts.length == 3)
                    .filter(parts -> (!parts[0].trim().isEmpty() && !parts[1].trim().isEmpty() && !parts[2].trim().isEmpty()))
                    .map(parts -> new Book.Builder()
                            .title(parts[0].trim())
                            .pages(Integer.parseInt(parts[1].trim()))
                            .year(Integer.parseInt(parts[2].trim()))
                            .build())
                    .collect(CustomArrayList.toCustomArrayList());
        }
    }

}
