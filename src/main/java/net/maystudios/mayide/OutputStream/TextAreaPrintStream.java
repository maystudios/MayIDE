package net.maystudios.mayide.OutputStream;

import javafx.scene.control.TextArea;
import net.maystudios.mayide.Gui.Widgets.Terminal.TerminalWidget;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class TextAreaPrintStream extends PrintStream {
    private TerminalWidget terminalWidget;

    public TextAreaPrintStream(TerminalWidget terminalWidget, OutputStream out) {
        super(out);
        this.terminalWidget = terminalWidget;
    }

    @Override
    public void println(String x) {
        System.out.println(x);
        terminalWidget.addRow(x);
    }
}