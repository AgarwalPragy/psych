package app.psych.game;

import lombok.Getter;
import lombok.Setter;

public class Pair<A, B> {
    @Getter
    @Setter
    private A first;
    @Getter
    @Setter
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }
}