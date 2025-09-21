package ru.team1.sorting.utils;

import ru.team1.sorting.model.Book;
import ru.team1.sorting.utils.exceptions.ParseBookException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualDataLoad extends DataLoad{
    public List<Book> loadManual(Scanner scanner, int num){
        if (num < 1) {
            throw new IllegalArgumentException("Размер должен быть больше 0");
        }
        System.out.println("Введите каждую книгу с новой строки в формате '<Название>, <кол-во страниц>, <год выпуска>'");

        List<Book> books = new ArrayList<>();

        try {
            for(int i = 0; i < num;){
                try {
                    if (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        System.out.println(line);
                        books.add(parseBook(line));
                        i++;
                    }
                }
                catch (ParseBookException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        catch (Exception e){
            throw new RuntimeException("Ошибка при чтении текущей строки",e);
        }

        return books;
    }
}
