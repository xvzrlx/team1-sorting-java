package ru.team1.sorting;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.team1.sorting.utils.design.MainPanel;



public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MainPanel mainPanel = new MainPanel();
        mainPanel.init();
        Scene scene = new Scene(mainPanel, 700, 600);
        stage.setTitle("Book sorting");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}