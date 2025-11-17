package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 */
public final class SimpleGUIWithFileChooser {
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame("My second Java graphical interface");

    /**
     * Sets up the GUI view.
     * 
     * @param controller the controller to use
     */
    public SimpleGUIWithFileChooser(final Controller controller) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        final JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        final JTextField text = new JTextField(controller.getCurrentFilePath());
        text.setEditable(false);

        final JButton save = new JButton("Save");
        panel.add(save, BorderLayout.SOUTH);
        final JButton browserButton = new JButton("Browse...");
        topPanel.add(text, BorderLayout.CENTER);
        topPanel.add(browserButton, BorderLayout.LINE_END);

        final JTextArea writeText = new JTextArea();
        panel.add(writeText, BorderLayout.CENTER);
        panel.add(topPanel, BorderLayout.NORTH);

        browserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ignored) {
                final JFileChooser fileChooser = new JFileChooser();
                final int returnValue = fileChooser.showOpenDialog(frame);
                switch (returnValue) {
                    case JFileChooser.APPROVE_OPTION:
                        final File file = fileChooser.getSelectedFile();
                        controller.setCurrentFile(file);
                        text.setText(controller.getCurrentFilePath());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, "Error during file selection", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ignored) {
                try {
                    controller.saveToFile(writeText.getText());
                } catch (final IOException e) {
                    JOptionPane.showMessageDialog(frame, "Error during file saving", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

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
        new SimpleGUIWithFileChooser(new Controller()).display();
    }

}
