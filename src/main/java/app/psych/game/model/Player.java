package app.psych.game.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
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

    @OneToOne(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Stats stats = new Stats();

    @ManyToMany(mappedBy = "players")
    @JsonIdentityReference
    @Getter
    @Setter
    private List<Game> games;

    private Player(Builder builder) {
        setName(builder.name);
        setPsychFaceURL(builder.psychFaceURL);
        setPicURL(builder.picURL);
    }

    public Player() {}

    public static final class Builder {
        private @NotBlank String name;
        private @URL String psychFaceURL;
        private @URL String picURL;

        public Builder() {
        }

        public Builder name(@NotBlank String val) {
            name = val;
            return this;
        }

        public Builder psychFaceURL(@URL String val) {
            psychFaceURL = val;
            return this;
        }

        public Builder picURL(@URL String val) {
            picURL = val;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }
}