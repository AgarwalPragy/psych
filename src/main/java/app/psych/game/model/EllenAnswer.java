package app.psych.game.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "ellen_answers")
public class EllenAnswer extends Auditable {
    @ManyToOne
    @Getter
    @Setter
    private Question question;

    @Getter
    @Setter
    @NotBlank
    private String answer;

    @Getter
    @Setter
    private Long votes = 0L;


}
