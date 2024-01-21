package net.maystudios.mayide.Gui.Widgets.Settings;

import javafx.scene.control.TextField;
import net.maystudios.mayide.Config.ConfigReader;
import net.maystudios.mayide.Config.StaticConfig;

import java.io.IOException;
import java.util.Map;

public class SettingsWidget extends TextField {

    private boolean writingEnabled = false;

    public SettingsWidget() {
        super();
    }

    public SettingsWidget(String text) {
        super(text);
    }

    public SettingsWidget(String text, String promptText) {
        super(text);
        setPromptText(promptText);
    }

    public SettingsWidget(String text, String promptText, String id) {
        super(text);
        setPromptText(promptText);
        setId(id);
    }

    public SettingsWidget(String text, String promptText, String id, String styleClass) {
        super(text);
        setPromptText(promptText);
        setId(id);
        getStyleClass().add(styleClass);
    }

    public void addRow(String text) {
        setText(getText() + "\n" + text);
    }

    public void setRowText(int row, String text) {
        String[] lines = getText().split("\n");
        lines[row] = text;
        setText(String.join("\n", lines));
    }

    public void toggleWriting(boolean writingEnabled) {
        setEditable(writingEnabled);
    }

    public void loadConfig() {
    ConfigReader configReader = new ConfigReader();
    try {
        Map<String, Object> config = configReader.readConfig(StaticConfig.CONFIG_FILE_PATH);
        for (Map.Entry<String, Object> entry : config.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String row = key + " = " + value.toString();
            addRow(row);
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
}
