package org.example;

import java.util.Scanner;

public class Menu {
    private final Method method;
    private final Scanner scanner = new Scanner(System.in);
    private final StringBuilder sessionLog = new StringBuilder();

    public Menu(Method method) {
        this.method = method;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("1- Introduce a text");
            System.out.println("2- Read a file");
            System.out.println("3- Count unique words");
            System.out.println("4- Count letters");
            System.out.println("5- Find word matches");
            System.out.println("6- Exit the app");
            System.out.println("Choose an option");
            String election = scanner.nextLine();
            sessionLog.append("User chose: ").append(election).append("\n");

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
                    method.readFile(filePath, sessionLog);
                    break;

                case "3":
                    System.out.print("Enter the text to analyze: ");
                    String textToAnalyze = scanner.nextLine();
                    int uniqueWordsCount = method.countUniqueWords(textToAnalyze);
                    sessionLog.append("Unique words count: ").append(uniqueWordsCount).append("\n");
                    System.out.println("Number of unique words: " + uniqueWordsCount);
                    break;

                case "4":
                    System.out.print("Enter the text to count letters: ");
                    String textToCountLetters = scanner.nextLine();
                    int letterCount = method.countLetters(textToCountLetters);
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

                    if (wordToFind.length() < 2) {
                        System.out.println("Error: The search word must contain at least 2 letters.");
                        sessionLog.append("Error: Search word too short\n");
                    } else {
                        int wordCount = method.countWordOccurrences(textToSearch, wordToFind);
                        sessionLog.append("Word occurrences: ").append(wordCount).append("\n");
                        System.out.println("Occurrences of \"" + wordToFind + "\": " + wordCount);
                    }
                    break;

                case "6":
                    System.out.println("Exiting...");
                    sessionLog.append("User exited the program\n");
                    method.writeLogToFile(sessionLog.toString());
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    sessionLog.append("Invalid choice\n");
                    break;
            }
        }
    }
}
