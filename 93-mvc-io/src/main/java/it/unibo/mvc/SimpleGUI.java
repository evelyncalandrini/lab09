package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A very simple program using a graphical interface.
 *
 */
public final class SimpleGUI {
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame();

    /**
     * Creates a new SimpleGUI.
     * 
     * @param controller the controller to be used by this GUI
     */
    public SimpleGUI(final SimpleController controller) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.setContentPane(panel);

        final JTextField textField = new JTextField();
        panel.add(textField, BorderLayout.NORTH);

        final JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        panel.add(textArea, BorderLayout.CENTER);

        final JPanel buttonPanel = new JPanel();
        panel.add(buttonPanel, BorderLayout.SOUTH);

        final JButton print = new JButton("Print");
        buttonPanel.add(print);

        final JButton showHistory = new JButton("Show History");
        buttonPanel.add(showHistory);

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ignored) {
                try {
                    controller.setNextString(textField.getText());
                    controller.printCurrentString();
                } catch (final IllegalArgumentException e) {
                    e.printStackTrace(); //NOPMD: print stack trace as required
                }
            } 
        });

        showHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ignored) {
                textArea.setText("");
                for (final String s : controller.getHistory()) {
                    textArea.append(s + "\n");
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
     * @param args method arguments
     */
    public static void main(final String[] args) {
        new SimpleGUI(new SimpleController()).display();
    }
}
