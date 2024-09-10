package org.example;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Method {
    private static final String LOG = "session_log.txt";

    public void readFile(String filePath, StringBuilder sessionLog) { //case 2
        try {
            String fileContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
            System.out.println("File content:\n" + fileContent);
            sessionLog.append("File content: \n").append(fileContent).append("\n");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            sessionLog.append("Error reading file: ").append(e.getMessage()).append("\n");
        }
    }

    public int countUniqueWords(String text) { //case 3
        if (StringUtils.isBlank(text)) return 0;

        String[] words = StringUtils.split(text.toLowerCase(), " ,.!?;:\n\t");
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));

        return uniqueWords.size();
    }

    public int countLetters(String text) { //case 4
        if (StringUtils.isBlank(text)) return 0;

        String textWithoutSpaces = StringUtils.deleteWhitespace(text);
        return textWithoutSpaces.length();
    }

    public int countWordOccurrences(String text, String word) {
        if (StringUtils.isBlank(text) || StringUtils.isBlank(word)) return 0;

        String[] words = StringUtils.split(text.toLowerCase(), " ,.!?;:\n\t");
        return Collections.frequency(Arrays.asList(words), word.toLowerCase());
    }

    public void writeLogToFile(String logContent) {
        try {
            FileUtils.writeStringToFile(new File(LOG), logContent, StandardCharsets.UTF_8, true);
            System.out.println("Session log written to " + LOG);
        } catch (IOException e) {
            System.out.println("Error writing log to file: " + e.getMessage());
        }
    }
}
