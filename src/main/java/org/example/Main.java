package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> words = new ArrayList<>();

        Map<String, Integer> wordsWithFreq = new HashMap<>();

        try {
            words = getInput();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        words.stream().forEach(s->wordsWithFreq.put(s, wordsWithFreq.getOrDefault(s, 0) + 1));

        for (String key : wordsWithFreq.keySet()) {
            System.out.println(key + " - " + wordsWithFreq.get(key));
        }
    }

    public static List<String> getInput() throws FileNotFoundException {

        final String PATH_TO_FILE = "src/main/resources/";

        List<String> wordsFromFile = new ArrayList<>();

        System.out.println("Введите имя файла:");
        Scanner input = new Scanner(System.in);

        File file = new File(PATH_TO_FILE + input.nextLine());
        input = new Scanner(file);

        while (input.hasNextLine()) {
            wordsFromFile.add(input.next());
        }
        input.close();

        return wordsFromFile;
    }

}