package net.maystudios.mayide.OutputStream;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger extends PrintStream {
    private PrintStream originalPrintStream;
    private PrintWriter filePrintWriter;

    public FileLogger(PrintStream originalPrintStream, String directoryPath) throws IOException {
        super(originalPrintStream);
        this.originalPrintStream = originalPrintStream;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();

        // Rename all existing .log files
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath), "*.log")) {
            for (Path path : stream) {
                File file = path.toFile();
                String newName = file.getName() + ".old";
                File newFile = new File(file.getParent(), newName);
                if (!file.renameTo(newFile)) {
                    System.out.println("Failed to rename file: " + file.getName());
                }
            }
        }

        // Create new log file
        String filePath = directoryPath + File.separator + dtf.format(now) + ".log";
        this.filePrintWriter = new PrintWriter(new FileOutputStream(filePath, true));
    }

    @Override
    public void println(String x) {
        originalPrintStream.println(x);
        filePrintWriter.println(x);
        filePrintWriter.flush();
        originalPrintStream.println("logged to file: " + x);
    }

    @Override
    public void println(Object x) {
        String s = String.valueOf(x);
        originalPrintStream.println(s);
        filePrintWriter.println(s);
        filePrintWriter.flush();
        originalPrintStream.println("logged to file: " + s);
    }
}