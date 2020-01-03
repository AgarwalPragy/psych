package app.psych.game.exceptions;

public class InvalidActionForGameStateException extends Throwable {
    public InvalidActionForGameStateException(String message) {
        super(message);
    }
}
