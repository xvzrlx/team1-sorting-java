package ru.team1.sorting.utils.design;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lombok.Getter;
import ru.team1.sorting.model.Book;
import ru.team1.sorting.utils.CustomArrayList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryPanel extends AbstractPanel {

    private static final int COLUMN_COUNT = 1;
    private static final int ROW_COUNT = 1;
    private static final String STYLE = "-fx-background-color: ffe3fe; -fx-border-color: black; -fx-border-width: 2;";

    @Getter
    private List<Button> bookButtons;
    @Getter
    private Map<Button, Book> bookMap;
    private CustomArrayList<Book> books;
    private VBox vBox;


    public LibraryPanel() {
        super(ROW_COUNT, COLUMN_COUNT, STYLE);
    }

    @Override
    protected void customInitialization() {
        vBox = new VBox();
        bookButtons = new ArrayList<>();
        bookMap = new HashMap<>();
        books = new CustomArrayList();
        ScrollPane scrollPane = new ScrollPane(vBox);
        vBox.setStyle("-fx-background-color: ffe3fe;");
        scrollPane.setStyle(STYLE);
        scrollPane.setFitToWidth(true);

        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        add(scrollPane, 0, 0);
    }

    public void addBook(Book book) {
        Button button = createButton(book.getTitle());
        bookMap.put(button, book);
        bookButtons.add(button);
        books.add(book);
        ImageView icon = new ImageView(new Image("/images/book_icon.png"));
        icon.setFitWidth(25);
        icon.setFitHeight(25);
        button.setGraphic(icon);

        button.setContentDisplay(ContentDisplay.LEFT);

        button.setStyle(
                "-fx-background-color: #f0f4f8;" +  // светлый фон
                        "-fx-text-fill: #333333;" +          // темный текст
                        "-fx-font-family: 'Segoe UI';" +     // современный шрифт
                        "-fx-font-size: 12px;" +
                        "-fx-background-radius: 6;" +
                        "-fx-border-radius: 6;" +
                        "-fx-border-color: transparent;" +
                        "-fx-padding: 6 12 6 12;" +
                        "-fx-cursor: hand;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 2,0,0,1);" // легкая тень
        );


        ScaleTransition hoverUp = new ScaleTransition(Duration.millis(100), button);
        hoverUp.setToX(1.01);
        hoverUp.setToY(1.01);

        ScaleTransition hoverDown = new ScaleTransition(Duration.millis(100), button);
        hoverDown.setToX(1.0);
        hoverDown.setToY(1.0);

        ScaleTransition pressDown = new ScaleTransition(Duration.millis(50), button);
        pressDown.setToX(0.95);
        pressDown.setToY(0.95);

        ScaleTransition releaseUp = new ScaleTransition(Duration.millis(50), button);
        releaseUp.setToX(1.0);
        releaseUp.setToY(1.0);

        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            hoverUp.playFromStart();
            button.setStyle(
                    "-fx-background-color: #f0f4f8;" +
                            "-fx-text-fill: #333333;" +
                            "-fx-font-family: 'Segoe UI';" +
                            "-fx-font-size: 12px;" +
                            "-fx-background-radius: 6;" +
                            "-fx-border-radius: 6;" +
                            "-fx-border-color: #7a7a7a;" +
                            "-fx-border-width: 2;" +
                            "-fx-padding: 6 12 6 12;" +
                            "-fx-cursor: hand;" +
                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 2,0,0,1);"
            );
        });
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            hoverDown.playFromStart();
            button.setStyle(
                    "-fx-background-color: #f0f4f8;" +
                            "-fx-text-fill: #333333;" +
                            "-fx-font-family: 'Segoe UI';" +
                            "-fx-font-size: 12px;" +
                            "-fx-background-radius: 6;" +
                            "-fx-border-radius: 6;" +
                            "-fx-border-color: transparent;" +
                            "-fx-border-width: 2;" +
                            "-fx-padding: 6 12 6 12;" +
                            "-fx-cursor: hand;" +
                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 2,0,0,1);"
            );
        });
        button.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> pressDown.playFromStart());
        button.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> releaseUp.playFromStart());

        vBox.getChildren().add(button);

    }

    private Button createButton(String name) {
        Button button = new Button(name);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return button;
    }
}
