package ru.team1.sorting.utils;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import ru.team1.sorting.model.Book;
import ru.team1.sorting.utils.exceptions.ParseBookException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
    public CustomArrayList<Book> getBooksFromGrids(List<GridPane> bookPanes) {
        if (bookPanes.isEmpty()) throw new RuntimeException("Сначала добавьте книги!");
        List<List<TextField>> textFields = bookPanes.stream()
                .map(grid -> grid.getChildren().stream()
                        .filter(TextField.class::isInstance)
                        .map(TextField.class::cast)
                        .collect(Collectors.toList()))
                .toList();
        List<TextField> emptyTextFields = textFields.stream()
                .flatMap(Collection::stream)
                .peek(this::checkTextField)
                .filter(textField -> textField.getText().isEmpty())
                .toList();
        if (!emptyTextFields.isEmpty()) throw new RuntimeException("Не должно быть пустых строк!");
        return textFields.stream()
                .map(fields -> new Book.Builder()
                        .title(fields.getFirst().getText().trim())
                        .year(Integer.parseInt(fields.get(1).getText()))
                        .pages(Integer.parseInt(fields.get(2).getText()))
                        .build())
                .collect(CustomArrayList.toCustomArrayList());
    }

    private void checkTextField(TextField textField) {
        if (textField.getText().isEmpty()) {
            textField.setStyle(
                    "-fx-border-color: red;" +
                            "-fx-border-width: 1;" +
                            "-fx-border-style: solid;"
            );
        } else textField.setStyle(null);
    }
}
