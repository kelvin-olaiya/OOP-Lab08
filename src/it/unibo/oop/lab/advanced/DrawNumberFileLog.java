package it.unibo.oop.lab.advanced;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class DrawNumberFileLog implements DrawNumberView {
    private static final String NEW_GAME_MSG = "New game starts";
    private static final String INCORRECT_ATTEMPT_MSG = "Wrong guess";
    private static final String LIMIT_REACHED_MSG = "Limit of attempts reached";
    private static final String SEP = File.separator; 
    private static final String logPath = System.getProperty("user.home") + SEP + "Desktop" + SEP + "DrawNumber.log.txt"; 
    private static final String NL = "\n";
    private DrawNumberViewObserver observer;
    private PrintStream logfile;

    @Override
    public void setObserver(final DrawNumberViewObserver observer) {
        this.observer = observer;
        try {
            logfile = new PrintStream(logPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        logfile.append(NEW_GAME_MSG + NL);
    }
    
    @Override
    public void numberIncorrect() {
        logfile.append(INCORRECT_ATTEMPT_MSG + NL);
    }

    @Override
    public void result(DrawResult res) {
        logfile.append(res.getDescription() + NL);
    }

    @Override
    public void limitsReached() {
        logfile.append(LIMIT_REACHED_MSG + NL);

    }

    @Override
    public void displayError(String message) {
        logfile.append("[error]: " + message + NL);
    }

}
