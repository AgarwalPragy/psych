package app.psych.game.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "players")
public class Player extends Auditable {
    @Getter
    @Setter
    @NotBlank
    private String name;

    @Getter
    @Setter
    @URL
    private String psychFaceURL;
    @Getter
    @Setter
    @URL
    private String picURL;

    @OneToOne
    @Getter
    @Setter
    private Stats stats;

    @ManyToMany(mappedBy = "players")
    @Getter
    @Setter
    private List<Game> games;

}