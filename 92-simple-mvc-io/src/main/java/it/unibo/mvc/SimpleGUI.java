package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame("My first Java graphcal interface");

    /**
     * Sets up the GUI view.
     * 
     * @param controller the controller to use
     */
    public SimpleGUI(final Controller controller) {
        final JPanel panel = new JPanel(); 
        panel.setLayout(new BorderLayout());

        final JTextArea text = new JTextArea();
        panel.add(text, BorderLayout.CENTER);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JButton save = new JButton("Save");
        panel.add(save, BorderLayout.SOUTH);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ignored) {
                try {
                    controller.saveToFile(text.getText());
                } catch (final IOException e) {
                    JOptionPane.showMessageDialog(frame, "Error during file saving", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace(); // NOPMD: allowed for debugging
                }
            }
        });
    }

    /**
     * Displays the GUI.
     */
    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Main method to start the program.
     * 
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        new SimpleGUI(new Controller()).display();
    }

}
