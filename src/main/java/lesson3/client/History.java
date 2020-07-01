package lesson3.client;

import javafx.scene.control.TextArea;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class History {
    private static final File historyFile = new File("history.txt");
    private static final int linesMax = 100;

    public static void saveToHistory(String msg){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(historyFile, true));
            if (!msg.startsWith("/clients")) {
                writer.write(msg + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromHistory(TextArea textArea) {
        try {
            BufferedReader read = new BufferedReader(new FileReader(historyFile));
            String line;
            if (countLines(historyFile) <= linesMax) {
                while ((line = read.readLine()) != null){
                    textArea.appendText(line + "\n");
                }
            } else {
                List<String> lines = new LinkedList<>();
                for(String tmp; (tmp = read.readLine()) != null;) {
                    lines.add(tmp);
                    if (lines.size() > linesMax) {
                        lines.remove(0);
                    }
                }
                for (int i = 0; i < lines.size(); i++) {
                    textArea.appendText(lines.get(i) + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        textArea.appendText("--история чата--\n");
    }

    public static int countLines(File file){
        int lineNumber = 0;
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
            while (lineNumberReader.readLine() != null){
                lineNumber++;
            }
            lineNumberReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return lineNumber;
    }
}