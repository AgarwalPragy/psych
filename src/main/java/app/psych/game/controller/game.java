package app.psych.game.controller;

import app.psych.game.Utils;
import app.psych.game.model.Game;
import app.psych.game.model.GameMode;
import app.psych.game.model.GameStatus;
import app.psych.game.model.Player;
import app.psych.game.repository.GameRepository;
import app.psych.game.repository.PlayerRepository;
import app.psych.game.repository.QuestionRepository;
import app.psych.game.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/game")
public class game {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    RoundRepository roundRepository;

    @GetMapping("/create/{pid}/{gm}/{nr}")
    public String createGame(@PathVariable(value = "pid") Long playerId,
                             @PathVariable(value = "gm") int gameMode,
                             @PathVariable(value = "nr") int numRounds) {
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        Player player = optionalPlayer.get();
        GameMode mode = GameMode.IS_THIS_A_FACT;

        Game game = new Game();
        game.setNumRounds(numRounds);
        game.setLeader(player);
        game.setGameMode(mode);
        game.getPlayers().add(player);

        gameRepository.save(game);

        return "" + game.getId() + "-" + Utils.getSecretCodeFromId(game.getId());
    }

    @GetMapping("/create/{pid}/{gc}")
    public String joinGame(@PathVariable(value = "pid") Long playerId,
                             @PathVariable(value = "gc") String gameCode) {
        Optional<Game> optionalGame = gameRepository.findById(Utils.getGameIdFromSecretCode(gameCode));
        Game game = optionalGame.get();
        if(!game.getGameStatus().equals(GameStatus.JOINING)) {
            // throw some error
        }
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        Player player = optionalPlayer.get();

        game.getPlayers().add(player);
        gameRepository.save(game);

        return "successfully joined";
    }

    // startGame - pid, gid/gc
    // pid is actually the leader of the current game
    // game has not already been started
    // the game has more than 1 players

    // endGame - pid gid
    // make sure that you're the leader of the game


    // getGameState - gid
    // JSON - current round - game stats of each player
    // - current round state - submitting-answer, selecting-answers-round-over

    // submitAnswer - pid, gid, answer

    // leaveGame - pid, gid
    // update player's stats

    // selectAnswer - pid, gid, answer-id
    // check if the answer is right or not,
    // update the and the game stats
    // to detect if the game has ended, and to end the game.
    // when the game ends, update every players stats

    // getReady - pid, gid
}


// pragy@interviewbit.com

