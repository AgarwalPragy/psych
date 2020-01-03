package app.psych.game;

import app.psych.game.model.EllenAnswer;
import app.psych.game.model.Round;

public interface EllenStrategy {
    EllenAnswer getAnswer(Round round);
}
