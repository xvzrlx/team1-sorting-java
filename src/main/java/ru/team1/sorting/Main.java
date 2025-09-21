package ru.team1.sorting;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.team1.sorting.utils.design.MainPanel;



public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MainPanel mainPanel = new MainPanel();
        stage.setOnCloseRequest(event -> {
            System.exit(0); // закрытие окна = завершение JVM
        });
        mainPanel.init();
        Scene scene = new Scene(mainPanel, 700, 600);
        stage.setTitle("Book sorting");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        Platform.exit();
        System.exit(1);
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

}