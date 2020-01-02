package app.psych.game.controller;

import app.psych.game.model.Game;
import app.psych.game.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dev")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/games/{id}")
    public List<Game> getAllGames(@PathVariable(value = "id") Long id) {
        return gameRepository.findAll();
    }
}
