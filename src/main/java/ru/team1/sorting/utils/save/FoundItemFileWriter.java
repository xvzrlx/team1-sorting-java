package ru.team1.sorting.utils.save;

import ru.team1.sorting.model.Book;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FoundItemFileWriter {
    public static void writeFoundItem(
            Book book,
            String filename) {

        if (book == null) {
            throw new IllegalArgumentException("Найденный элемент не может быть null");
        }
        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя файла не может быть пустым");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            String line = formatBookToCsv(book);
            writer.write(line);
            writer.newLine();

            System.out.println("Записана найденная книга в файл: " + filename);

        } catch (IOException e) {
            throw new RuntimeException("Ошибка при записи в файл: " + filename, e);
        }
    }

    private static String formatBookToCsv(Book book) {
        return String.format("%s,%d,%d",
                escapeCsv(book.getTitle()),
                book.getPages(),
                book.getYear());
    }

    private static String escapeCsv(String field) {
        if (field == null) return "";
        if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }
}
