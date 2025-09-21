package ru.team1.sorting.utils.design;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractPanel extends GridPane {
    private final int rowCount;
    private final int columnCount;
    private final String style;

    public void init() {
        createGrid();
        customInitialization();
        visualProperty();
    }

    private void createGrid() {
        double gap = 1.5;
        double padding = 1.5;
        for (int i = 0; i < columnCount; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth((double) 100 / columnCount);
            getColumnConstraints().add(columnConstraints);
            columnConstraints.setHgrow(Priority.ALWAYS);
            columnConstraints.setFillWidth(true);
        }
        for (int i = 0; i < rowCount; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight((double) 100 / rowCount);
            getRowConstraints().add(rowConstraints);
            rowConstraints.setVgrow(Priority.ALWAYS);
            rowConstraints.setFillHeight(true);
        }
        setVgap(gap);
        setHgap(gap);
        setPadding(new Insets(padding, padding, padding, padding));
    }

    private void visualProperty() {
        setStyle(style);
    }

    protected abstract void customInitialization();

    protected GridPane createGrid(int columnCount, int rowCount) {
        GridPane gridPane = new GridPane();
        for (int i = 0; i < columnCount; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(100.0 / columnCount);
            gridPane.getColumnConstraints().add(col);
        }
        for (int i = 0; i < rowCount; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100.0 / rowCount);
            gridPane.getRowConstraints().add(row);
        }
        return gridPane;
    }

    protected void addBackGround(GridPane pane, String imagePath) {
        Image image = new Image(getClass().getResource(imagePath).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        90, 90,
                        true,
                        true,
                        true,
                        false
                )
        );
        pane.setBackground(new Background(
                List.of(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)),
                List.of(backgroundImage)
        ));
    }

    protected void loadImageToButton(Button button, String releasedPath, String pressedPath) {
        button.setText("");
        button.setGraphic(null);
        ScaleTransition scaleUpPressed = new ScaleTransition(Duration.millis(50), button);
        scaleUpPressed.setToX(1.0);
        scaleUpPressed.setToY(1.0);
        ScaleTransition scaleDownReleased = new ScaleTransition(Duration.millis(50), button);
        scaleDownReleased.setToX(0.9);
        scaleDownReleased.setToY(0.9);
        ScaleTransition scaleUpHovered = new ScaleTransition(Duration.millis(50), button);
        scaleUpHovered.setToX(1.04);
        scaleUpHovered.setToY(1.04);
        ScaleTransition scaleDownUnHovered = new ScaleTransition(Duration.millis(50), button);
        scaleDownUnHovered.setToX(1.0);
        scaleDownUnHovered.setToY(1.0);
        button.setStyle("-fx-cursor: hand;");

        setImageToButton(button, releasedPath);

        button.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            scaleDownReleased.playFromStart();
        });
        button.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            scaleUpPressed.playFromStart();
        });
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            setImageToButton(button, pressedPath);
            scaleUpHovered.playFromStart();
        });
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            setImageToButton(button, releasedPath);
            scaleDownUnHovered.playFromStart();
        });
    }

    private void setImageToButton(Button button, String imagePath) {
        Image pressedImage = new Image(getClass().getResource(imagePath).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(
                pressedImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(
                        90, 90,
                        true,
                        true,
                        true,
                        false)
        );
        button.setBackground(new Background(
                List.of(new BackgroundFill(
                        Color.web("d6ffff"),
                        CornerRadii.EMPTY,
                        Insets.EMPTY)),
                List.of(backgroundImage)));
    }

    protected void addOnlyDigitsConstraint(TextField textField) {
        textField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[0-9]*")) {
                return change;
            }
            return null;
        }));
    }

}
