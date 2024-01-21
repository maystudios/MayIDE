package net.maystudios.mayide.Gui.Widgets.Terminal;

import javafx.scene.control.TextArea;

import java.util.HashSet;
import java.util.Set;

public class TerminalWidget extends TextArea {

    private boolean userWriting = true;
    private Set<Integer> programLines = new HashSet<>();

    public TerminalWidget() {
        super();
        addCaretListener();
    }

    public TerminalWidget(String text) {
        super(text);
        addCaretListener();
    }

    private void addCaretListener() {
        caretPositionProperty().addListener((observable, oldValue, newValue) -> {
            String[] lines = getText().split("\n");
            int currentLine = getCurrentLine();
            toggleWriting(userWriting && currentLine == lines.length - 1);
        });
    }

    private int getCurrentLine() {
        String textBeforeCaret = getText().substring(0, getCaretPosition());
        return textBeforeCaret.split("\n", -1).length - 1;
    }

    public void addRow(String text) {
        userWriting = false;
        setText(getText() + "\n<MayTerminal> : " + text);
        programLines.add(getText().split("\n").length - 1);
        setText(getText() + "\n<MayTerminal> : ");
        userWriting = true;
    }

    public void setRowText(int row, String text) {
        userWriting = false;
        String[] lines = getText().split("\n");
        lines[row] = "<MayTerminal> : " + text;
        setText(String.join("\n", lines));
        userWriting = true;
    }

    public void toggleWriting(boolean writingEnabled) {
        setEditable(writingEnabled);
    }
}