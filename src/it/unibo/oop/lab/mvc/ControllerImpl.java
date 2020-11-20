package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ControllerImpl implements Controller {
    private final List<String> stringHistory;
    private String stringToPrint;
    /**
     * Initialize Controller.
     */
    public ControllerImpl() {
        stringHistory = new ArrayList<>();
    }
    /**
     * @param s string to be set as next
     */
    public void setNextString(final String s) {
        this.stringToPrint = Objects.requireNonNull(s);
    }
    /**
     * @return the next string
     */
    public String getNextString() {
        return this.stringToPrint;
    }
    /**
     * @return A List representing history
     */
    public List<String> getStringHistory() {
        return new ArrayList<>(this.stringHistory);
    }
    /**
     * @return currentString
     */
    public void printString() {
        if (Objects.isNull(this.getNextString())) {
            throw new IllegalArgumentException();
        }
        this.stringHistory.add(this.stringToPrint);
        System.out.println(this.getNextString());
    }
}
