package org.example;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static final String LOG = "session_log.txt";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sessionLog= new StringBuilder();

        while(true) {
            System.out.println("1- Introduce a text");
            System.out.println("2- Read a file");
            System.out.println("3- Count unique words ");
            System.out.println("4- Count letters ");
            System.out.println("5- Find word matches");
            System.out.println("6- Exit the app");
            System.out.println("Choose an option");
            String election = scanner.nextLine();
            sessionLog.append("User choosed: ").append(election).append("\n");

            switch (election) {
                case "1":
                    System.out.print("Enter text: ");
                    String inputText = scanner.nextLine();
                    sessionLog.append("User entered text: ").append(inputText).append("\n");
                    System.out.println("You entered: " + inputText);
                    break;

                case "2":
                    System.out.print("Enter file path: ");
                    String filePath = scanner.nextLine();
                    sessionLog.append("User entered file path: ").append(filePath).append("\n");
                    try {
                        String fileContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
                        System.out.println("File content:\n" + fileContent);
                        sessionLog.append("File content: \n").append(fileContent).append("\n");
                    } catch (IOException e) {
                        System.out.println("Error reading file: " + e.getMessage());
                        sessionLog.append("Error reading file: ").append(e.getMessage()).append("\n");
                    }
                    break;

                case "3":
                    System.out.print("Enter the text to analyze: ");
                    String textToAnalyze = scanner.nextLine();
                    int uniqueWordsCount = countUniqueWords(textToAnalyze);
                    sessionLog.append("Unique words count: ").append(uniqueWordsCount).append("\n");
                    System.out.println("Number of unique words: " + uniqueWordsCount);
                    break;

                case "4":
                    System.out.print("Enter the text to count letters: ");
                    String textToCountLetters = scanner.nextLine();
                    int letterCount = countLetters(textToCountLetters);
                    sessionLog.append("Letter count (no spaces): ").append(letterCount).append("\n");
                    System.out.println("Number of letters (excluding spaces): " + letterCount);
                    break;

                case "5":
                    System.out.print("Enter text: ");
                    String textToSearch = scanner.nextLine();
                    System.out.print("Enter a word to search: ");
                    String wordToFind = scanner.nextLine();
                    sessionLog.append("Text to search: ").append(textToSearch).append("\n");
                    sessionLog.append("Word to find: ").append(wordToFind).append("\n");

                    if (wordToFind.length() < 2) { //word has to be more than 2 letters
                        System.out.println("Error: The search word must contain at least 2 letters.");
                        sessionLog.append("Error: Search word too short\n");
                    } else {
                        int wordCount = countWordOccurrences(textToSearch, wordToFind);
                        sessionLog.append("Word occurrences: ").append(wordCount).append("\n");
                        System.out.println("Occurrences of \"" + wordToFind + "\": " + wordCount);
                    }
                    break;

                case "6":
                    System.out.println("Exiting...");
                    sessionLog.append("User exited the program\n");
                    writeLogToFile(sessionLog.toString());
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    sessionLog.append("Invalid choice\n");
                    break;

            }
        }


    }
    private static int countUniqueWords(String text) {
        if (StringUtils.isBlank(text)) return 0;

        String[] words = StringUtils.split(text.toLowerCase(), " ,.!?;:\n\t");
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));

        return uniqueWords.size();
    }

    private static int countLetters(String text) {
        if (StringUtils.isBlank(text)) return 0;

        String textWithoutSpaces = StringUtils.deleteWhitespace(text);
        return StringUtils.countMatches(textWithoutSpaces, "") - 1;
    }

    private static int countWordOccurrences(String text, String word) {
        if (StringUtils.isBlank(text) || StringUtils.isBlank(word)) return 0;

        String[] words = StringUtils.split(text.toLowerCase(), " ,.!?;:\n\t");
        return Collections.frequency(Arrays.asList(words), word.toLowerCase());
    }

    private static void writeLogToFile(String logContent) {
        try {
            FileUtils.writeStringToFile(new File(LOG), logContent, StandardCharsets.UTF_8, true);
            System.out.println("Session log written to " + LOG);
        } catch (IOException e) {
            System.out.println("Error writing log to file: " + e.getMessage());
        }
    }
}