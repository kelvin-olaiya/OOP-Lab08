package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {

    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int ATTEMPTS = 10;
    private static final int NUM_CONFIG = 3;
    private final DrawNumber model;
    private List<DrawNumberView> views = new ArrayList<>();

    /**
     * 
     */
    public DrawNumberApp() {
        this.model = readConfiguration();
        this.views = List.of(new DrawNumberViewImpl(), new DrawNumberLog(), new DrawNumberFileLog());
        this.views.stream().forEach(e -> e.setObserver(this));
        this.views.stream().forEach(e -> e.start());
    }
    
    private DrawNumber readConfiguration() {
        int min = MIN;
        int max = MAX;
        int attempts = ATTEMPTS;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("config.yml")))) {
            String configuration;
            while ((configuration = in.readLine()) != null) {
                final StringTokenizer st = new StringTokenizer(configuration, ":");
                switch (st.nextToken()) {
                case "minimum":
                    min = Integer.parseInt(st.nextToken().trim());
                    break;
                case "maximum":
                    max = Integer.parseInt(st.nextToken().trim());
                    break;
                case "attempts":
                    attempts = Integer.parseInt(st.nextToken().trim());
                    break;
                default:
                    break;
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return new DrawNumberImpl(min, max, attempts);
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            this.views.stream().forEach(e -> e.result(result));
        } catch (IllegalArgumentException e) {
            this.views.stream().forEach(v -> v.numberIncorrect());
        } catch (AttemptsLimitReachedException e) {
            this.views.stream().forEach(v -> v.limitsReached());
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        new DrawNumberApp();
    }

}
