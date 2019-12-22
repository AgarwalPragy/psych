package app.psych.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "games")
public class Game extends Auditable {
    @Getter
    @Setter
    @NotNull
    private int numRounds;

    @Getter
    @Setter
    private int currentRound = 0;

    @ManyToMany
    @Getter
    @Setter
    private Map<Player, Stats> playerStats;

    @ManyToMany
    @Getter
    @Setter
    private List<Player> players;

    @NotNull
    @Getter
    @Setter
    private GameMode gameMode;

    @Getter
    @Setter
    private GameStatus gameStatus = GameStatus.JOINING;

    @ManyToOne
    @NotNull
    @Getter
    @Setter
    private Player leader;

    @OneToMany(mappedBy = "game")
    @Getter @Setter
    private List<Round> rounds;
}