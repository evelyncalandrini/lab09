package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {
    private String nextString;
    private final List<String> history = new ArrayList<>();

    @Override
    public void setNextString(final String nextString) {
        if (nextString == null) {
            throw new IllegalArgumentException("Null string not allowed");
        }
        this.nextString = nextString;
        this.history.add(nextString);
    }

    @Override
    public String getNextString() {
        return this.nextString;
    }

    @Override
    public List<String> getHistory() {
        return new ArrayList<>(this.history);
    }

    @Override
    public void printCurrentString() {
        if (this.nextString == null) {
            throw new IllegalStateException("No string set");
        }
        System.out.println(this.nextString); // NOPMD: print to stdout as required
    }
}
