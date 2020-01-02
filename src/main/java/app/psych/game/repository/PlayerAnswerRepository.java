package app.psych.game.repository;

import app.psych.game.model.PlayerAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerAnswerRepository extends JpaRepository<PlayerAnswer, Long> {
}
