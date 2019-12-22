package app.psych.game.model;

import app.psych.game.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "player_answers")
public class PlayerAnswer extends Auditable {
    @Getter
    @Setter
    @NotBlank
    @Column(length = Constants.MAX_ANSWER_LENGTH)
    private String answer;

    @Getter
    @Setter
    private Round round;

    @Getter
    @Setter
    @NotNull
    private Player player;
}
