package it.unibo.oop.lab.advanced;

public class DrawNumberLog implements DrawNumberView {
    private static final String NEW_GAME_MSG = "New game starts";
    private static final String INCORRECT_ATTEMPT_MSG = "Wrong guess";
    private static final String LIMIT_REACHED_MSG = "Limit of attempts reached";
    
    private DrawNumberViewObserver observer;

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
        this.observer = observer;
    }

    @Override
    public void start() {
        System.out.println(NEW_GAME_MSG);
    }

    @Override
    public void numberIncorrect() {
        System.out.println(INCORRECT_ATTEMPT_MSG);
    }

    @Override
    public void result(DrawResult res) {
        System.out.println(res.getDescription());
    }

    @Override
    public void limitsReached() {
        System.out.println(LIMIT_REACHED_MSG);

    }

    @Override
    public void displayError(String message) {
        System.out.println("[error]: " + message);

    }

}
