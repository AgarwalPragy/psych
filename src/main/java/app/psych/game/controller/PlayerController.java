package app.psych.game.controller;

import app.psych.game.model.Player;
import app.psych.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dev")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/players/{id}")
    public List<Player> getAllPlayers(@PathVariable(value = "id") Long id) {
        return playerRepository.findAll();
    }
}
