package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private File currentFile;

    /**
     * Default controller constructor. Sets the default file to "output.txt"
     */
    public Controller() {
        final String home = System.getProperty("user.home");
        final String separator = System.getProperty("file.separator");
        this.currentFile = new File(home + separator + "output.txt");
    }

    /**
     * Sets the current file.
     * 
     * @param file the file to set as current
     */
    public void setCurrentFile(final File file) {
        this.currentFile = file;
    }

    /**
     * Returns the current file.
     * 
     * @return the current file
     */
    public File getCurrentFile() {
        return this.currentFile;
    }

    /**
     * Returns the current file path.
     * 
     * @return the current file path
     */
    public String getCurrentFilePath() {
        return this.currentFile.getPath();
    }
    /**
     * Saves the given text to the current file.
     * 
     * @param text the text to save
     */

    public void saveToFile(final String text) throws IOException {
        try (PrintStream ps = new PrintStream(this.currentFile, StandardCharsets.UTF_8)) {
            ps.println(text);
        } catch (final IOException e) {
            throw new IOException("Error during file saving", e);
        }
    }

}
