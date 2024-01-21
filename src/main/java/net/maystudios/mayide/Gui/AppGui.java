package net.maystudios.mayide.Gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import net.maystudios.mayide.Compiler.Theme.Theme;
import net.maystudios.mayide.Compiler.Theme.ThemeMay;
import net.maystudios.mayide.Config.ConfigReader;
import net.maystudios.mayide.Gui.Widgets.Code.CodeArea;
import net.maystudios.mayide.Gui.Widgets.Settings.SettingsWidget;
import net.maystudios.mayide.Gui.Widgets.Terminal.TerminalWidget;
import net.maystudios.mayide.OutputStream.FileLogger;
import net.maystudios.mayide.OutputStream.TextAreaPrintStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class AppGui extends Application {


    private CodeArea codeArea;
    private Button compileButton;
    private TextField folderField;
    private TextField fileField;
    private TerminalWidget terminalArea;

    private SettingsWidget settingsWidget;

    private ConfigReader configReader = new ConfigReader();

    @Override
    public void start(Stage primaryStage) {
        // Create the folder and file creation fields
        folderField = new TextField();
        fileField = new TextField();

        // Create the terminal area
        terminalArea = new TerminalWidget();

        // Create the code area
        Theme theme = new ThemeMay();
        codeArea = new CodeArea(theme);



        // Create the settings widget
        settingsWidget = new SettingsWidget("Settings", "Settings", "settings-widget", "settings-widget");
        settingsWidget.toggleWriting(false);
        settingsWidget.loadConfig();



        // Create the compile button
        compileButton = new Button("Compile");
        compileButton.setOnAction(e -> compileCode());

        // Set the layout manager
        BorderPane borderPane = new BorderPane();
        HBox topPanel = new HBox(folderField, fileField, compileButton);
        borderPane.setTop(topPanel);
        borderPane.setCenter(codeArea);
        borderPane.setRight(settingsWidget); // Add the settings widget to the right
        borderPane.setBottom(terminalArea);

        // Set the scene and stage
        Scene scene = new Scene(borderPane, 1280, 720);
        scene.getStylesheets().add("styles.css"); // Add the CSS file to the scene
        primaryStage.setTitle("Simple IDE");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Redirect standard output and error streams to the terminal area
        PrintStream printStream = new PrintStream(new TextAreaPrintStream(terminalArea, System.out));
        System.setOut(printStream);
        System.setErr(printStream);

        String logDirectory = null;
        try {
            logDirectory = configReader.readConfig("D:/Development/Java/MayIDE/config/settings.config").get("log_directory").toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Log directory: " + logDirectory);

        PrintStream fileLogger;
        try {
            fileLogger = new FileLogger(printStream, logDirectory);
            System.setOut(fileLogger);
            System.setErr(fileLogger);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void compileCode() {
        // Compilation logic goes here
        terminalArea.addRow("Compiling code...");
        terminalArea.addRow(codeArea.getText());
        terminalArea.addRow("Code compiled successfully!");
    }
}
