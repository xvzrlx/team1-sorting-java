package ru.team1.sorting.utils.design;

import javafx.scene.layout.GridPane;

public class DynamicVisionPanel extends AbstractPanel {

    private static final int COLUMN_COUNT = 1;
    private static final int ROW_COUNT = 1;
    private static final String STYLE = "-fx-border-color: black; -fx-border-width: 2;";

    public DynamicVisionPanel() {
        super(ROW_COUNT, COLUMN_COUNT, STYLE);
    }

    @Override
    protected void customInitialization() {

    }
}
