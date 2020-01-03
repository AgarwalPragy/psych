package app.psych.game.controller;

import app.psych.game.Utils;
import app.psych.game.exceptions.IllegalGameException;
import app.psych.game.exceptions.InsufficientPlayersException;
import app.psych.game.exceptions.InvalidActionForGameStateException;
import app.psych.game.exceptions.InvalidInputException;
import app.psych.game.model.*;
import app.psych.game.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/play")
public class PlayEndpoint {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    RoundRepository roundRepository;
    @Autowired
    PlayerAnswerRepository playerAnswerRepository;

    @GetMapping("/create-game/{pid}/{gm}/{nr}/{ellen}")
    public String createGame(@PathVariable(value = "pid") Long playerId,
                             @PathVariable(value = "gm") int gameMode,
                             @PathVariable(value = "nr") int numRounds,
                             @PathVariable(value = "ellen") int hasEllen) {
        Player player = playerRepository.findById(playerId).orElseThrow();
        Game game = new Game.Builder()
                .hasEllen(hasEllen == 1)
                .numRounds(numRounds)
                .gameMode(GameMode.fromValue(gameMode))
                .leader(player)
                .build();
        gameRepository.save(game);
        return "Created game: " + game.getId() + ". Code: " + Utils.getSecretCodeFromId(game.getId());
    }

    @GetMapping("/join-game/{pid}/{gc}")
    public String joinGame(@PathVariable(value = "pid") Long playerId,
                           @PathVariable(value = "gc") String gameCode) throws InvalidActionForGameStateException {
        Game game = gameRepository.findById(Utils.getGameIdFromSecretCode(gameCode)).orElseThrow();
        Player player = playerRepository.findById(playerId).orElseThrow();
        game.addPlayer(player);
        gameRepository.save(game);
        return "joined game";
    }

    @GetMapping("/start-game/{pid}/{gid}")
    public String startGame(@PathVariable(value = "pid") Long playerId,
                           @PathVariable(value = "gid") Long gameId) throws InvalidActionForGameStateException, IllegalGameException, InsufficientPlayersException {
        Game game = gameRepository.findById(gameId).orElseThrow();
        Player player = playerRepository.findById(playerId).orElseThrow();
        if(!game.getLeader().equals(player)) {
            throw new IllegalGameException("Player hasn't joined any such game");
        }
        if(game.getPlayers().size() < 2) {
            throw new InsufficientPlayersException("Cannot start a game without any friends");
        }
        game.start();
        gameRepository.save(game);
        return "game started";
    }

    @GetMapping("/submit-answer/{pid}/{gid}/{answer}")
    public String submitAnswer(@PathVariable(value = "pid") Long playerId,
                               @PathVariable(value = "gid") Long gameId,
                               @PathVariable(value = "answer") String answer) throws InvalidActionForGameStateException, IllegalGameException {
        Game game = gameRepository.findById(gameId).orElseThrow();
        Player player = playerRepository.findById(playerId).orElseThrow();
        if(!game.hasPlayer(player)) {
            throw new IllegalGameException("Player has not joined the game yet");
        }
        game.submitAnswer(player, answer);
        gameRepository.save(game);
        return "submitted answer";
    }

    @GetMapping("/select-answer/{pid}/{gid}/{paid}")
    public String selectAnswer(@PathVariable(value = "pid") Long playerId,
                               @PathVariable(value = "gid") Long gameId,
                               @PathVariable(value = "paid") Long playerAnswerId) throws InvalidActionForGameStateException, IllegalGameException, InvalidInputException {
        Game game = gameRepository.findById(gameId).orElseThrow();
        Player player = playerRepository.findById(playerId).orElseThrow();
        PlayerAnswer playerAnswer = playerAnswerRepository.findById(playerAnswerId).orElseThrow();
        if(!game.hasPlayer(player)) {
            throw new IllegalGameException("Player has not joined the game yet");
        }
        game.selectAnswer(player, playerAnswer);
        gameRepository.save(game);
        return "selected answer";
    }


    @GetMapping("/get-ready/{pid}/{gid}")
    public String getReady(@PathVariable(value = "pid") Long playerId,
                               @PathVariable(value = "gid") Long gameId) throws IllegalGameException, InvalidInputException {
        Game game = gameRepository.findById(gameId).orElseThrow();
        Player player = playerRepository.findById(playerId).orElseThrow();
        if(!game.hasPlayer(player)) {
            throw new IllegalGameException("Player has not joined the game yet");
        }
        game.getReady(player);
        gameRepository.save(game);
        return "player ready";
    }

    @GetMapping("/game-state/{gid}")
    public String gameState(@PathVariable(value = "gid") Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow();
        return game.getState();
    }

    // endGame - pid gid
    // make sure that you're the leader of the game

    // leaveGame - pid, gid
    // update player's stats
}


// pragy@interviewbit.com

