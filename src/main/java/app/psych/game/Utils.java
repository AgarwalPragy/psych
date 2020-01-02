package app.psych.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Utils {

    private static List<String> wordsList;
    private static Map<String, Integer> wordIndices;

    static {
        wordsList = new ArrayList<>();
        wordIndices = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("words.txt"));
            String word;
            do {
                word = br.readLine();
                wordsList.add(word);
                wordIndices.put(word, wordsList.size() - 1);
            } while (word != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Pair<String, String>> readQAFile(String filename) {
        String question, answer;
        List<Pair<String, String>> question_answers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            do {
                question = br.readLine();
                answer = br.readLine();
                if (question == null || answer == null || question.length() > Constants.MAX_QUESTION_LENGTH - 1 || answer.length() > Constants.MAX_ANSWER_LENGTH - 1) {
                    System.out.println("Skipping question: " + question);
                    System.out.println("Skipping answer: " + answer);
                    continue;
                }
                question_answers.add(new Pair<>(question, answer));
            } while (question != null & answer != null);
        } catch (IOException ignored) {
        }
        return question_answers;
    }

    public static String getSecretCodeFromId(Long id) {
        int base = wordsList.size();
        String code = "";
        while (id > 0) {
            code += wordsList.get((int) (id % base)) + " ";
            id /= base;
        }
        return code;
    }
    public static long getGameIdFromSecretCode(String code) {
        String[] words = code.split(" ");
        long gameId = 0L;
        int base = wordsList.size();
        for(String word: words) {
            int index = wordIndices.get(word);
            gameId = gameId * base + index;
        }
        return gameId;
    }
}
