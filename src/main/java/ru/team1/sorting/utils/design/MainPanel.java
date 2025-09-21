package ru.team1.sorting.utils.design;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ru.team1.sorting.model.Book;
import ru.team1.sorting.utils.FileDataLoad;

import java.io.IOException;
import java.util.List;

public class MainPanel extends AbstractPanel {

    private static final int ROW_COUNT = 10;
    private static final int COLUMN_COUNT = 10;
    private static final String STYLE = "";

    private final ConsolePanel consolePanel = new ConsolePanel();
    private final DynamicVisionPanel dynamicVisionPanel = new DynamicVisionPanel();
    private final LibraryPanel libraryPanel = new LibraryPanel();
    private final LoadingPanel loadingPanel = new LoadingPanel();
    private final PropertyPanel propertyPanel = new PropertyPanel();

    public MainPanel() {
        super(ROW_COUNT, COLUMN_COUNT, STYLE);
    }

    @Override
    protected void customInitialization() {
        componentsInit();
        addComponents();
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
                List<Book> books = loadingPanel.getBooksFromGrids();
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
                List<Book> books;
                try {
                    books = new FileDataLoad().loadFromFileByStream(text);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                books.forEach(libraryPanel::addBook);
                handleBookButtons();
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
