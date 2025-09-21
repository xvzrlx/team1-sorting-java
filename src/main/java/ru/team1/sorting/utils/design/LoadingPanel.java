package ru.team1.sorting.utils.design;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class LoadingPanel extends AbstractPanel {

    private static final int COLUMN_COUNT = 1;
    private static final int ROW_COUNT = 1;
    private static final String STYLE = "-fx-background-color: d6ffff; -fx-border-color: black; -fx-border-width: 2;";

    private GridPane manualInputPanel;
    private GridPane inputFromFilePanel;
    private GridPane randomInputPanel;

    private ComboBox<String> inputOption;

    @Getter
    private Button addBooks;

    @Getter
    private TextField filePathTextField;
    @Getter
    private Button loadFromFileButton;

    @Getter
    private Button randomLoadButton;

    @Getter
    private List<GridPane> bookPanes = new ArrayList<>();

    private int bookGridsCount = 0;


    public LoadingPanel() {
        super(ROW_COUNT, COLUMN_COUNT, STYLE);
    }

    @Override
    protected void customInitialization() {
        manualInputPanelInit();
        inputFromFilePanelInit();
        randomInputPanelInit();
        inputOptionInit();
    }

    private void manualInputPanelInit() {
        manualInputPanel = createGrid(12, 7);
        manualInputPanel.setHgap(2);
        manualInputPanel.setVgap(2);
        Button add = new Button("+");
        Button remove = new Button("-");
        addBooks = new Button("Добавить книги");
        add.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        remove.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addBooks.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        loadImageToButton(
                add,
                "/images/plus_button.png",
                "/images/plus_button_hovered.png",
                "d6ffff"
        );
        loadImageToButton(
                remove,
                "/images/minus_button.png",
                "/images/minus_button_hovered.png",
                "d6ffff"
        );
        loadImageToButton(
                addBooks,
                "/images/add.png",
                "/images/add_hovered.png",
                "d6ffff"
        );

        manualInputPanel.add(add, 5, 0);
        manualInputPanel.add(remove, 6, 0);
        manualInputPanel.add(addBooks, 7, 0, 3, 1);

        add(manualInputPanel, 0 , 0);

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                addBookPane();
            }
        });

        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                removeLastBookPane();
            }
        });
    }

    private void inputFromFilePanelInit() {
        inputFromFilePanel = createGrid(12, 7);

        filePathTextField = new TextField();
        loadFromFileButton = new Button();

        filePathTextField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        loadFromFileButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        inputFromFilePanel.add(new Label("Введите путь к файлу:"), 3, 2, 6, 1);
        inputFromFilePanel.add(filePathTextField, 3, 3, 6, 1);
        inputFromFilePanel.add(loadFromFileButton, 9, 3);

        loadImageToButton(
                loadFromFileButton,
                "/images/load.png",
                "/images/load_hovered.png",
                "d6ffff"
        );
    }

    private void randomInputPanelInit() {
        randomInputPanel = createGrid(12, 7);
        randomLoadButton = new Button();
        randomLoadButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        randomInputPanel.add(randomLoadButton, 4, 2, 4, 4);
        loadImageToButton(
                randomLoadButton,
                "/images/rand_button.png",
                "/images/rand_button_hovered.png",
                "d6ffff"
                );
    }

    private void inputOptionInit() {
        inputOption = new ComboBox<>();
        inputOption.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        inputOption.getItems().addAll("Ручной ввод", "Загрузка из файла", "Рандом");
        inputOption.setValue("Ручной ввод");

        manualInputPanel.add(inputOption, 0, 0 , 4, 1);


        inputOption.setStyle(
                "-fx-background-color: #f0f4f8;" +
                        "-fx-border-color: #a0a0a0;" +
                        "-fx-border-radius: 6;" +
                        "-fx-background-radius: 6;" +
                        "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 12px;" +
                        "-fx-text-fill: #333333;" +
                        "-fx-cursor: hand;"
        );

        inputOption.setOnMouseEntered(e -> inputOption.setStyle(
                "-fx-background-color: #e1e8f0;" +
                        "-fx-border-color: #7a7a7a;" +
                        "-fx-border-radius: 6;" +
                        "-fx-background-radius: 6;" +
                        "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 12px;" +
                        "-fx-text-fill: #333333;" +
                        "-fx-cursor: hand;"
        ));

        inputOption.setOnMouseExited(e -> inputOption.setStyle(
                "-fx-background-color: #f0f4f8;" +
                        "-fx-border-color: #a0a0a0;" +
                        "-fx-border-radius: 6;" +
                        "-fx-background-radius: 6;" +
                        "-fx-font-family: 'Segoe UI';" +
                        "-fx-font-size: 12px;" +
                        "-fx-text-fill: #333333;" +
                        "-fx-cursor: hand;"
        ));

        inputOption.setOnAction(event -> {
            String selected = inputOption.getValue();
            switch (selected) {
                case "Ручной ввод" -> changeInputOption(manualInputPanel);
                case "Загрузка из файла" -> changeInputOption(inputFromFilePanel);
                case "Рандом" -> changeInputOption(randomInputPanel);
            }
        });
    }

    private void changeInputOption(GridPane gridPane) {
        if (!gridPane.getChildren().contains(inputOption)) {
            gridPane.add(inputOption, 0, 0 , 4, 1);
        }
        getChildren().clear();
        add(gridPane, 0, 0);
    }

    private void addBookPane() {
        if (bookGridsCount >= 6) return;

        int rowIndex = 1 + (bookGridsCount / 3) * 3;
        int colIndex = (bookGridsCount % 3) * 4;

        GridPane bookGridPane = createBookGridPane();

        Label title = new Label(" Название:");
        title.setFont(Font.font("Georgia", 12));
        Label year = new Label(" Год выпуска:");
        year.setFont(Font.font("Georgia", 11));
        Label pages = new Label(" Страницы:");
        pages.setFont(Font.font("Georgia", 12));

        bookGridPane.add(title, 0, 0);
        bookGridPane.add(year, 0, 1);
        bookGridPane.add(pages, 0, 2);
        String textFieldStyle =
                "-fx-background-color: white;" +
                        "-fx-border-color: #c0c0c0;" +
                        "-fx-border-radius: 6;" +
                        "-fx-background-radius: 6;" +
                        "-fx-padding: 4;" +
                        "-fx-font-size: 12px;" +
                        "-fx-font-family: 'Segoe UI';";

        List<TextField> fields = List.of(new TextField(), new TextField(), new TextField());
        for (int i = 0; i < 3; i++){
            bookGridPane.add(fields.get(i), 1, i);
            fields.get(i).setStyle(textFieldStyle);
        }
        addOnlyDigitsConstraint(fields.get(1));
        addOnlyDigitsConstraint(fields.get(2));

        manualInputPanel.add(bookGridPane, colIndex, rowIndex, 4,3);
        bookGridsCount++;
        bookPanes.add(bookGridPane);
    }

    private GridPane createBookGridPane() {
        GridPane bookPane = new GridPane();
        bookPane.setHgap(2);
        bookPane.setVgap(2);
        bookPane.setPadding(new Insets(7));
        for (int i = 0; i < 2; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(50);
            bookPane.getColumnConstraints().add(col);
        }
        for (int i = 0; i < 3; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / 3);
            bookPane.getRowConstraints().add(row);
        }
        bookPane.setStyle(
                "-fx-background-color: #f9f9f9;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-color: #d3d3d3;" +
                        "-fx-border-width: 1;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 4,0,0,2);"
        );
        return bookPane;
    }

    private void removeLastBookPane() {
        if (bookPanes.isEmpty()) return;
        manualInputPanel.getChildren().remove(bookPanes.getLast());
        bookPanes.removeLast();
        bookGridsCount--;
    }

    public void clearManualInputBookPane() {
        manualInputPanel.getChildren().removeAll(bookPanes);
        bookGridsCount = 0;
    }





}
