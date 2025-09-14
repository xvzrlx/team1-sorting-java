package ru.team1.sorting;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean dataLoaded = false;

    public static void main(String[] args) {
        System.out.println("Sorting App");

        while (true) {
            showMainMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1 -> handleDataLoading();
                case 2 -> handleDataAdding();
                case 3 -> handleSearch();
                case 4 -> handleSorting();
                case 0 -> {
                    System.out.println("👋 До свидания!");
                    return;
                }
                default -> System.out.println("❌ Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\n=== Главное меню ===");
        System.out.println("1. Заполнить данные");
        System.out.println("2. Добавить данные");
        System.out.println("3. Найти элемент");
        System.out.println("4. Сортировка");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private static void handleDataLoading() {
        System.out.println("\n--- Заполнение данных ---");
        System.out.println("1. Из файла");
        System.out.println("2. Рандом");
        System.out.println("3. Вручную");
        System.out.println("4. Stream");
        System.out.print("Выберите способ: ");
        int choice = getIntInput();

        switch (choice) {
            case 1 -> {
                System.out.println("📂 Загрузка из файла");
                dataLoaded = true;
            }
            case 2 -> {
                System.out.print("Введите размер: ");
                int size = getIntInput();
                System.out.println("🎲 Рандомная генерация");
                dataLoaded = true;
            }
            case 3 -> {
                System.out.println("✍️ Ручной ввод");
                dataLoaded = true;
            }
            case 4 -> {
                System.out.println("🌊 Загрузка через Stream");
                dataLoaded = true;
            }
            default -> System.out.println("❌ Неверный выбор.");
        }
    }

    private static void handleDataAdding() {
        System.out.println("➕ Добавление данных");
    }

    private static void handleSearch() {
        if (!dataLoaded) {
            System.out.println("❌ Нет данных. Сначала заполните коллекцию.");
            return;
        }
        System.out.println("\n--- Поиск ---");
        System.out.println("1. Бинарный поиск");
        System.out.println("2. Поиск по элементу");
        System.out.print("Выберите тип поиска: ");
        int choice = getIntInput();

        switch (choice) {
            case 1 -> {
                System.out.println("Бинарный поиск");
            }
            case 2 -> {
                System.out.println("Поиск по элементу");
            }
            default -> System.out.println("❌ Неверный выбор.");
        }
    }

    private static void handleSorting() {
        if (!dataLoaded) {
            System.out.println("❌ Нет данных. Сначала заполните коллекцию.");
            return;
        }
        System.out.println("\n--- Сортировка ---");
        System.out.println("1. По названию");
        System.out.println("2. По году");
        System.out.println("3. По году (только чётные)");
        System.out.print("Выберите тип сортировки: ");
        int choice = getIntInput();

        switch (choice) {
            case 1 -> {
                System.out.println("🔤 Сортировка по названию");
            }
            case 2 -> {
                System.out.println("📅 Сортировка по году");
            }
            case 3 -> {
                System.out.println("🔢 Сортировка по году (только чётные)");
            }
            default -> System.out.println("❌ Неверный выбор.");
        }
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