package app.psych.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<Pair<String, String>> readQAFile(String filename) {

        String question, answer;
        List<Pair<String, String>> question_answers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("qa_facts.txt"))) {
            do {
                question = br.readLine();
                answer = br.readLine();
                if (question == null || answer == null || question.length() > Constants.MAX_QUESTION_LENGTH -1 || answer.length() > Constants.MAX_ANSWER_LENGTH -1) {
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
}
