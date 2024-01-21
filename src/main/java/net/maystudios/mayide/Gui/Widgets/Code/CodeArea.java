package net.maystudios.mayide.Gui.Widgets.Code;

import net.maystudios.mayide.Compiler.Lexer.Lexer;
import net.maystudios.mayide.Compiler.Theme.Theme;
import org.fxmisc.richtext.StyleClassedTextArea;

import java.util.List;

public class CodeArea extends StyleClassedTextArea {

    private Theme activeTheme;
    public CodeArea(Theme theme) {
        super();
        initialize();
        this.activeTheme = theme;
    }

    private void initialize() {
        this.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Text changed from " + oldValue + " to " + newValue);
            colorizeText();
        });
    }

    public void colorizeTextAll() {
        String text = this.getText();
        // Clear previous styles
        clearStyle(0, text.length());
        // Add new style
        setStyleClass(0, text.length(), "red");
    }

    public void colorizeText() {
        String text = this.getText();
        // Clear previous styles
        clearStyle(0, text.length());

        List<String>[] tokens = Lexer.lex(text);
        for (int i = 0; i < tokens[0].size(); i++) {
            String token = tokens[0].get(i);
            String tokenText = tokens[1].get(i);
            String styleClass = activeTheme.getColorForTokenType(token);
            setStyleClass(i, i + 1, styleClass);
        }

    }

    public void colorizeText(int startIndex, int length, String styleClass) {
        // Clear previous styles
        clearStyle(startIndex, length);
        // Add new style
        setStyleClass(startIndex, length, styleClass);
    }
}