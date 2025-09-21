package ru.team1.sorting.utils.design;

import javafx.scene.control.TextArea;
import ru.team1.sorting.model.Book;

public class ConsolePanel extends AbstractPanel {

    private static final int COLUMN_COUNT = 1;
    private static final int ROW_COUNT = 1;
    private static final String STYLE = "-fx-background-color: black; -fx-border-color: black; -fx-border-width: 0;";

    private final TextArea console = new TextArea();


    public ConsolePanel() {
        super(ROW_COUNT, COLUMN_COUNT, STYLE);
    }

    private void addText(String text) {
        console.appendText(text);
    }

    public void clean() {
        console.clear();
    }

    @Override
    protected void customInitialization() {
        consoleInit();
    }

    private void consoleInit() {
        console.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        console.setStyle("-fx-control-inner-background: black;" +
                "-fx-text-fill: green;" +
                "-fx-font-family: 'Consolas';" +
                "-fx-highlight-fill: gray;" +
                "-fx-highlight-text-fill: black;");
        console.setEditable(false);
        console.setWrapText(true);

        console.getStylesheets().add(getClass().getResource("/console.css").toExternalForm());
        add(console, 0,0 );
    }

    public void printBook(Book book) {
        addText("Название: %s\nГод выпуска: %s\nКоличество страниц: %s\n"
                .formatted(
                        book.getTitle(),
                        book.getYear(),
                        book.getPages()
                )
        );
        addText("------------------------------------------------------------\n");
    }

    public void print(String text) {
        addText(text+"\n");
    }

    public void printBookFromSearch(Book book, long countInCollection) {
        if (book == null) {
            print("Книга не найдена!");
            return;
        }
        addText("Найденная книга\n");
        if (countInCollection > 0)
            addText("Количество экземпляров: " + countInCollection +"\n");
        printBook(book);

    }

}
