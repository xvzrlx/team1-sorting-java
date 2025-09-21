package ru.team1.sorting.utils.design;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ru.team1.sorting.model.Book;
import ru.team1.sorting.services.sorting.ClassSorting;
import ru.team1.sorting.utils.CustomArrayList;
import ru.team1.sorting.utils.FileDataLoad;
import ru.team1.sorting.utils.ManualDataLoad;
import ru.team1.sorting.utils.RandomDataLoad;

import java.io.IOException;

public class MainPanel extends AbstractPanel {

    private static final int ROW_COUNT = 10;
    private static final int COLUMN_COUNT = 10;
    private static final String STYLE = "";

    private final ConsolePanel consolePanel = new ConsolePanel();
    private final DynamicVisionPanel dynamicVisionPanel = new DynamicVisionPanel();
    private final LibraryPanel libraryPanel = new LibraryPanel();
    private final LoadingPanel loadingPanel = new LoadingPanel();
    private final PropertyPanel propertyPanel = new PropertyPanel();
    private final ManualDataLoad manualDataLoad = new ManualDataLoad();
    private final RandomDataLoad randomDataLoad = new RandomDataLoad();
    private final ClassSorting classSorting = new ClassSorting();

    public MainPanel() {
        super(ROW_COUNT, COLUMN_COUNT, STYLE);
    }

    @Override
    protected void customInitialization() {
        componentsInit();
        addComponents();
        randomDataLoad.addBooks();
    }

    private void componentsInit() {
        consolePanel.init();
        dynamicVisionPanel.init();
        libraryPanel.init();
        loadingPanel.init();
        propertyPanel.init();
        buttonsHandler();
    }

    private void addComponents() {
        add(consolePanel, 0, 6, 10, 4);
        add(libraryPanel, 7, 0, 3, 6);
        add(propertyPanel, 0, 4, 2, 2);
        add(dynamicVisionPanel, 2, 4, 5, 2);
        add(loadingPanel, 0, 0, 7, 4);
    }

    private void buttonsHandler() {
        loadingPanel.getAddBooks().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CustomArrayList<Book> books = manualDataLoad.getBooksFromGrids(loadingPanel.getBookPanes());
                loadingPanel.clearManualInputBookPane();
                books.forEach(libraryPanel::addBook);
                handleBookButtons();
            }
        });
        propertyPanel.getCleanButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                consolePanel.clean();
            }
        });
        loadingPanel.getLoadFromFileButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String text = loadingPanel.getFilePathTextField().getText();
                if (text.isEmpty()) {
                    loadingPanel.getFilePathTextField().setStyle(
                            "-fx-border-color: red;" +
                                    "-fx-border-width: 1;" +
                                    "-fx-border-style: solid;"
                    );
                    throw new RuntimeException("Путь к файлу не должен быть пустым!");
                }
                loadingPanel.getFilePathTextField().setStyle(null);
                CustomArrayList<Book> books;
                try {
                    books = new FileDataLoad().loadFromFileByStream(text);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                books.forEach(libraryPanel::addBook);
                handleBookButtons();
            }
        });
        loadingPanel.getRandomLoadButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CustomArrayList<Book> randomBooks = randomDataLoad.getRandomBooks();
                randomBooks.forEach(libraryPanel::addBook);
                handleBookButtons();
            }
        });
        propertyPanel.getFindButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dynamicVisionPanel.search();
            }
        });
        dynamicVisionPanel.getSearchButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        propertyPanel.getSortButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dynamicVisionPanel.sort();
            }
        });
        dynamicVisionPanel.getSortButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ClassSorting.sort(libraryPanel.getBooks(), dynamicVisionPanel.getSortType().getSortingStrategy());
                libraryPanel.removeAllBooks();
                libraryPanel.getBooks().forEach(libraryPanel::addBook);
                if (dynamicVisionPanel.isPrintToConsole()) libraryPanel.getBooks().forEach(consolePanel::printBook);
            }
        });
    }

    private void handleBookButtons() {
        libraryPanel.getBookButtons()
                .forEach(button -> button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        consolePanel.printBook(libraryPanel.getBookMap().get(button));
                    }
                }));
    }

}
