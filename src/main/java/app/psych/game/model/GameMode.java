package app.psych.game.model;

public enum GameMode {
    IS_THIS_A_FACT(1),
    UNSCRAMBLE(2),
    WORD_UP(3);

    private int value;

    GameMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static GameMode fromValue(int value) {
        switch (value) {
            case 1:
                return GameMode.IS_THIS_A_FACT;
            case 2:
                return GameMode.UNSCRAMBLE;
            case 3:
                return GameMode.WORD_UP;
        }
        return IS_THIS_A_FACT;
    }
}
