package net.maystudios.mayide;

import javafx.application.Application;
import javafx.stage.Stage;
import net.maystudios.mayide.Gui.AppGui;

public class SimpleIDE extends Application {

    private AppGui appGui;

    public static void main(String[] args) {
        Application.launch(SimpleIDE.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        appGui = new AppGui();
        appGui.start(primaryStage);
    }
}