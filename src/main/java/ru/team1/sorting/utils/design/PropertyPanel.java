package ru.team1.sorting.utils.design;

import javafx.scene.control.Button;
import lombok.Getter;

@Getter
public class PropertyPanel extends AbstractPanel {

    private static final int COLUMN_COUNT = 2;
    private static final int ROW_COUNT = 2;
    private static final String STYLE = "-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2;";

    private Button cleanButton;
    private Button sortButton;
    private Button findButton;

    public PropertyPanel() {
        super(ROW_COUNT, COLUMN_COUNT, STYLE);
    }

    @Override
    protected void customInitialization() {
        initButtons();
        addButtons();
    }

    private void initButtons() {
        cleanButton = new Button();
        cleanButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        sortButton = new Button();
        sortButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        findButton = new Button();
        findButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        loadImageToButton(
                cleanButton,
                "/images/clean_console.png",
                "/images/clean_console_hovered.png",
                "white"
        );
        loadImageToButton(
                findButton,
                "/images/search_button.png",
                "/images/search_button_hovered.png",
                "white"
        );
        loadImageToButton(
                sortButton,
                "/images/sort_icon.png",
                "/images/sort_icon_hovered.png",
                "white"
        );
    }

    private void addButtons() {
        add(sortButton, 0, 0);
        add(cleanButton, 1, 0);
        add(findButton, 0, 1);
    }
}
