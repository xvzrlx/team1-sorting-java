package ru.team1.sorting.utils.design;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import lombok.Getter;
import ru.team1.sorting.services.sorting.SortType;

import java.util.List;


public class DynamicVisionPanel extends AbstractPanel {

    private static final int COLUMN_COUNT = 1;
    private static final int ROW_COUNT = 1;
    private static final String STYLE = "-fx-background-color: ffd4fe;-fx-border-color: black; -fx-border-width: 2;";

    private GridPane searchPane;
    @Getter
    private Button searchButton;
    @Getter
    private Button saveToFileAfterSearch;


    private GridPane sortPane;
    @Getter
    private Button sortButton;
    @Getter
    private boolean defaultSort = true;
    private RadioButton sortByTitle;
    private RadioButton sortByYear;
    private RadioButton sortByPages;
    @Getter
    private Button printToConsoleButton;
    @Getter
    private SortType sortTypeToSort = SortType.BY_TITLE;
    @Getter
    private boolean printToConsole;
    @Getter
    private List<TextField> textFields;

    private GridPane saveToFilePane;
    @Getter
    private Button saveToFileButton;
    @Getter
    private TextField filePathTextField;

    public DynamicVisionPanel() {
        super(ROW_COUNT, COLUMN_COUNT, STYLE);
    }

    @Override
    protected void customInitialization() {
        searchInit();
        sortInit();
        saveToFileInit();
    }

    private void searchInit() {
        searchPane = createGrid(8, 3);
        searchButton = new Button();
        saveToFileAfterSearch = new Button();

        searchButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        saveToFileAfterSearch.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        searchPane.add(new Label("Название:"), 0, 0, 3, 1);
        searchPane.add(new Label("Год выпуска:"), 0, 1, 3, 1);
        searchPane.add(new Label("Количество страниц:"), 0, 2, 3, 1);

        String textFieldStyle =
                "-fx-background-color: white;" +
                        "-fx-border-color: #c0c0c0;" +
                        "-fx-border-radius: 6;" +
                        "-fx-background-radius: 6;" +
                        "-fx-padding: 4;" +
                        "-fx-font-size: 12px;" +
                        "-fx-font-family: 'Segoe UI';";

        textFields = List.of(new TextField(), new TextField(), new TextField());
        for (int i = 0; i < 3; i++) {
            searchPane.add(textFields.get(i), 3, i, 3, 1);
            textFields.get(i).setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            textFields.get(i).setStyle(textFieldStyle);
            if (i > 0) addOnlyDigitsConstraint(textFields.get(i));
        }

        for (TextField tf : textFields) {
            tf.textProperty().addListener((obs, oldVal, newVal) -> {
                if (!newVal.isBlank()) {
                    for (TextField other : textFields) {
                        if (other != tf) {
                            other.clear();
                        }
                    }
                }
            });
        }

        loadImageToButton(
                searchButton,
                "/images/search_button.png",
                "/images/search_button_hovered.png",
                "ffd4fe"
        );
        loadImageToButton(
                saveToFileAfterSearch,
                "/images/load_to_file.png",
                "/images/load_to_file_hovered.png",
                "ffd4fe"
        );
        searchPane.add(saveToFileAfterSearch, 7, 2);
        searchPane.add(searchButton, 6, 0, 2,2);
    }

    public void search() {
        getChildren().clear();
        add(searchPane, 0, 0);
    }

    private void sortInit() {
        sortPane = createGrid(7, 10);
        sortButton = new Button();
        printToConsoleButton = new Button();
        Button defaultSortButton = new Button();
        sortPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        defaultSortButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        sortByTitle = new RadioButton("Сортировать по названиям");
        sortByYear = new RadioButton("Сортировать по году");
        sortByPages = new RadioButton("Сортировать по страницам");

        ToggleGroup sortGroup = new ToggleGroup();
        sortByTitle.setToggleGroup(sortGroup);
        sortByYear.setToggleGroup(sortGroup);
        sortByPages.setToggleGroup(sortGroup);

        sortByTitle.setSelected(true);

        sortGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                RadioButton selected = (RadioButton) newVal;
                if (selected == sortByTitle) {
                    sortTypeToSort = SortType.BY_TITLE;
                } else if (selected == sortByYear) {
                   sortTypeToSort = SortType.BY_YEAR;
                } else if (selected == sortByPages) {
                    sortTypeToSort = SortType.BY_PAGES;
                }
            }
        });

        printToConsoleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (printToConsole) {
                    loadImageToButton(
                            printToConsoleButton,
                            "/images/print_to_console.png",
                            "/images/print_to_console.png",
                            "ffd4fe"
                    );
                } else {
                    loadImageToButton(
                            printToConsoleButton,
                            "/images/print_to_console_hovered.png",
                            "/images/print_to_console_hovered.png",
                            "ffd4fe"
                    );
                }
                printToConsole = !printToConsole;
            }
        });

        defaultSortButton.setStyle(
                "-fx-background-color: blue;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-color: blue;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-background-radius: 5px;");

        defaultSortButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                defaultSort = !defaultSort;
                if (defaultSort) {
                    defaultSortButton.setStyle(
                            "-fx-background-color: blue;" +
                                    "-fx-text-fill: white;" +
                                    "-fx-border-color: red;" +
                                    "-fx-border-width: 1px;" +
                                    "-fx-border-radius: 5px;" +
                                    "-fx-background-radius: 5px;"
                    );
                } else {
                    defaultSortButton.setStyle(
                            "-fx-background-color: blue;" +
                            "-fx-text-fill: white;" +
                            "-fx-border-color: blue;" +
                            "-fx-border-width: 1px;" +
                            "-fx-border-radius: 5px;" +
                            "-fx-background-radius: 5px;"
                    );
                }
            }
        });

        loadImageToButton(
                printToConsoleButton,
                "/images/print_to_console.png",
                "/images/print_to_console_hovered.png",
                "ffd4fe"
        );

        loadImageToButton(
                sortButton,
                "/images/sort_icon.png",
                "/images/sort_icon_hovered.png",
                "ffd4fe"
        );

        sortByTitle.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        sortByYear.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        sortByPages.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        printToConsoleButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        sortButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        sortPane.add(sortByTitle, 0, 0, 4, 2);
        sortPane.add(sortByPages, 0, 3, 4, 2);
        sortPane.add(sortByYear, 0, 6, 4, 2);
        sortPane.add(printToConsoleButton, 4, 0, 2, 5);
        sortPane.add(sortButton, 4, 5, 2, 5);
        sortPane.add(defaultSortButton, 6, 0, 1, 2);
    }

    public void sort() {
        getChildren().clear();
        add(sortPane, 0 , 0);
    }

    private void saveToFileInit() {
        saveToFilePane = createGrid(7, 4);
        saveToFileButton = new Button();
        filePathTextField = new TextField();
        saveToFileButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        filePathTextField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        loadImageToButton(
                saveToFileButton,
                "/images/load_to_file.png",
                "/images/load_to_file_hovered.png",
                "ffd4fe"
        );

        saveToFilePane.add(new Label("Введите путь к файлу:"), 1, 1, 4, 1);
        saveToFilePane.add(filePathTextField, 1, 2, 4, 1);
        saveToFilePane.add(saveToFileButton, 5, 2);
    }

    public void saveToFile() {
        getChildren().clear();
        add(saveToFilePane, 0, 0);
    }

}
