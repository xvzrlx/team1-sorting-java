package ru.team1.sorting;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ru.team1.sorting.model.Book;
import ru.team1.sorting.utils.FileDataLoad;
import ru.team1.sorting.utils.design.MainPanel;

import java.util.List;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MainPanel mainPanel = new MainPanel();
        mainPanel.init();
        Scene scene = new Scene(mainPanel, 700, 600);
        stage.setTitle("Book sorting");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


//    private static final Scanner scanner = new Scanner(System.in);
//    private static List<Book> currentBooks = null;

//    public static void main(String[] args) {
//        System.out.println("Sorting App");
//
//        while (true) {
//            showMainMenu();
//            int choice = getIntInput();
//
//            switch (choice) {
//                case 1 -> handleDataLoading();
//                case 2 -> handleDataAdding();
//                case 3 -> handleSearch();
//                case 4 -> handleSorting();
//                case 0 -> {
//                    System.out.println("👋 До свидания!");
//                    return;
//                }
//                default -> System.out.println("❌ Неверный выбор. Попробуйте снова.");
//            }
//        }
//    }
//
//    private static void showMainMenu() {
//        System.out.println("\n=== Главное меню ===");
//        System.out.println("1. Заполнить данные");
//        System.out.println("2. Добавить данные");
//        System.out.println("3. Найти элемент");
//        System.out.println("4. Сортировка");
//        System.out.println("0. Выход");
//        System.out.print("Выберите действие: ");
//    }
//
//    private static void handleDataLoading() {
//        System.out.println("\n--- Заполнение данных ---");
//        System.out.println("1. Из файла");
//        System.out.println("2. Рандом");
//        System.out.println("3. Вручную");
//        System.out.println("4. Stream");
//        System.out.print("Выберите способ: ");
//        int choice = getIntInput();
//        try {
//            switch (choice) {
//                case 1 -> {
//                    System.out.print("Введите путь к файлу: ");
//                    String path = scanner.nextLine().trim();
//                    FileDataLoad loader = new FileDataLoad();
//                    currentBooks = loader.loadFromFile(path);
//                    //временно/выгрузка содержимого коллекции в консоль
//                    System.out.println("📚 Содержимое загруженной коллекции:");
//                    for (int i = 0; i < currentBooks.size(); i++) {
//                        System.out.println((i + 1) + ". " + currentBooks.get(i));
//                    }
//                }
//                case 2 -> {
//                    System.out.print("Введите размер: ");
//                    int size = getIntInput();
//                    System.out.println("🎲 Рандомная генерация");
//                }
//                case 3 -> {
//                    System.out.println("✍️ Ручной ввод");
//                }
//                case 4 -> {
//                    System.out.println("🌊 Загрузка через Stream");
//                }
//                default -> System.out.println("❌ Неверный выбор.");
//            }
//            if (currentBooks != null) {
//                System.out.println("✅ Данные загружены. Количество: " + currentBooks.size());
//            }
//        } catch (Exception e) {
//            System.out.println("❌ Ошибка: " + e.getMessage());
//        }
//    }
//
//    private static void handleDataAdding() {
//        System.out.println("➕ Добавление данных");
//    }
//
//    private static void handleSearch() {
//        if (currentBooks == null || currentBooks.isEmpty()) {
//            System.out.println("❌ Нет данных. Сначала заполните коллекцию.");
//            return;
//        }
//        System.out.println("\n--- Поиск ---");
//        System.out.println("1. Бинарный поиск");
//        System.out.println("2. Поиск по элементу");
//        System.out.print("Выберите тип поиска: ");
//        int choice = getIntInput();
//
//        switch (choice) {
//            case 1 -> {
//                System.out.println("Бинарный поиск");
//            }
//            case 2 -> {
//                System.out.println("Поиск по элементу");
//            }
//            default -> System.out.println("❌ Неверный выбор.");
//        }
//    }
//
//    private static void handleSorting() {
//        if (currentBooks == null || currentBooks.isEmpty()) {
//            System.out.println("❌ Нет данных. Сначала заполните коллекцию.");
//            return;
//        }
//        System.out.println("\n--- Сортировка ---");
//        System.out.println("1. По названию");
//        System.out.println("2. По году");
//        System.out.println("3. По году (только чётные)");
//        System.out.print("Выберите тип сортировки: ");
//        int choice = getIntInput();
//
//        switch (choice) {
//            case 1 -> {
//                System.out.println("🔤 Сортировка по названию");
//            }
//            case 2 -> {
//                System.out.println("📅 Сортировка по году");
//            }
//            case 3 -> {
//                System.out.println("🔢 Сортировка по году (только чётные)");
//            }
//            default -> System.out.println("❌ Неверный выбор.");
//        }
//    }
//
//    private static int getIntInput() {
//        while (true) {
//            try {
//                return Integer.parseInt(scanner.nextLine().trim());
//            } catch (NumberFormatException e) {
//                System.out.print("❌ Введите число: ");
//            }
//        }
//    }
}