package ru.team1.sorting.utils.save;
import ru.team1.sorting.model.Book;
import ru.team1.sorting.services.sorting.SortByTitle;
import ru.team1.sorting.services.sorting.SortByYear;
import ru.team1.sorting.services.sorting.SortByPages;
import ru.team1.sorting.utils.ManualDataLoad;
import java.util.List;
import java.util.Scanner;

public class SaveSortedCollection {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ManualDataLoad manualLoader = new ManualDataLoad();

    public static void main(String[] args) {

        System.out.print("Введите количество книг: ");
        int size = getIntInput();

        List<Book> books = manualLoader.loadManual(scanner, size);

        System.out.println("Выберите тип сортировки:");
        System.out.println("1. По названию");
        System.out.println("2. По году");
        System.out.println("3. По страницам");
        int choice = getIntInput();

        switch (choice) {
            case 1 -> {
                System.out.println("Сортировка по названию...");
                SortedFileWriter.writeSortedToFile(books, "sorted_by_title.csv", new SortByTitle<>());
            }
            case 2 -> {
                System.out.println("Сортировка по году...");
                SortedFileWriter.writeSortedToFile(books, "sorted_by_year.csv", new SortByYear<>());
            }
            case 3 -> {
                System.out.println("Сортировка по страницам...");
                SortedFileWriter.writeSortedToFile(books, "sorted_by_pages.csv", new SortByPages<>());
            }
            default -> {
                System.out.println("Неверный выбор. Используется сортировка по названию.");
                SortedFileWriter.writeSortedToFile(books, "sorted_by_title.csv", new SortByTitle<>());
            }
        }
        System.out.println("Готово! Файл сохранён.");
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("❌ Введите число: ");
            }
        }
    }
}
