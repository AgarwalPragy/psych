package app.psych.game.controller;

import app.psych.game.Constants;
import app.psych.game.Pair;
import app.psych.game.Utils;
import app.psych.game.model.GameMode;
import app.psych.game.model.Player;
import app.psych.game.model.Question;
import app.psych.game.repository.PlayerRepository;
import app.psych.game.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/dev")
public class PopulateDB {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/add-questions-from-files")
    public void addQuestionsFromFiles() throws IOException {
        questionRepository.deleteAll();
        for (Map.Entry<String, GameMode> entry : Constants.QA_FILES.entrySet()) {
            int questionNumber = 0;
            String filename = entry.getKey();
            GameMode gameMode = entry.getValue();
            for (Pair<String, String> question_answer : Utils.readQAFile(filename)) {
                Question q = new Question();
                q.setQuestionText(question_answer.getFirst());
                q.setCorrectAnswer(question_answer.getSecond());
                q.setGameMode(gameMode);
                questionRepository.save(q);
                questionNumber++;
                if (questionNumber > Constants.MAX_QUESTIONS_TO_READ) {
                    break;
                }
            }
        }
    }

    @GetMapping("/add-dummy-players")
    public void addDummyPlayers() throws IOException {
        playerRepository.deleteAll();
        Player luffy = new Player();
        luffy.setName("Monkey D. Luffy");
        Player robin = new Player();
        robin.setName("Nico Robin");

        playerRepository.save(luffy);
        playerRepository.save(robin);
    }
}
