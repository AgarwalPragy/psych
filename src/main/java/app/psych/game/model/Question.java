package app.psych.game.model;

import app.psych.game.Constants;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question extends Auditable {
    @NotBlank
    @Getter
    @Setter
    @Column(length = Constants.MAX_QUESTION_LENGTH)
    private String questionText;

    @NotBlank
    @Getter
    @Setter
    @Column(length = Constants.MAX_ANSWER_LENGTH)
    private String correctAnswer;

    @NotNull
    @Getter
    @Setter
    private GameMode gameMode;

    @OneToMany(mappedBy = "question")
    @JsonManagedReference
    @Getter
    @Setter
    private List<EllenAnswer> ellenAnswers = new ArrayList<>();

    public Question() {
    }

    private Question(Builder builder) {
        setQuestionText(builder.questionText);
        setCorrectAnswer(builder.correctAnswer);
        setGameMode(builder.gameMode);
    }

    public static final class Builder {
        private @NotBlank String questionText;
        private @NotBlank String correctAnswer;
        private @NotNull GameMode gameMode;

        public Builder() {
        }

        public Builder questionText(@NotBlank String val) {
            questionText = val;
            return this;
        }

        public Builder correctAnswer(@NotBlank String val) {
            correctAnswer = val;
            return this;
        }

        public Builder gameMode(@NotNull GameMode val) {
            gameMode = val;
            return this;
        }

        public Question build() {
            return new Question(this);
        }
    }
}
