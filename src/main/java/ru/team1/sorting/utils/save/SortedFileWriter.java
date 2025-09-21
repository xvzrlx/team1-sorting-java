package ru.team1.sorting.utils.save;
import ru.team1.sorting.model.Book;
import ru.team1.sorting.services.sorting.SortingStrategy;
import ru.team1.sorting.services.sorting.ClassSorting;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SortedFileWriter {
    public static <T extends Book> void writeSortedToFile(
            List<T> books,
            String filename,
            SortingStrategy<T> strategy) {

        if (books == null || books.isEmpty()) {
            throw new IllegalArgumentException("Коллекция не может быть пустой");
        }
        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя файла не может быть пустым");
        }
        ClassSorting.sort(books, strategy);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            for (Book book : books) {
                String line = String.format("%s,%d,%d",
                        escapeCsv(book.getTitle()),
                        book.getPages(),
                        book.getYear());
                writer.write(line);
                writer.newLine();
            }

            System.out.println("Записано " + books.size() + " книг в файл: " + filename);

        } catch (IOException e) {
            throw new RuntimeException("Ошибка при записи в файл: " + filename, e);
        }
    }
    private static String escapeCsv(String field) {
        if (field == null) return "";
        if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }
}
