package hi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class represents a virtual piano that can be played using a computer keyboard.
 * It is built using Java Swing for GUI and Java Sound API for playing MIDI sounds.
 */
public class VirtualPianoView extends JPanel implements KeyListener {

    // MIDI synthesizer for generating sound and MIDI channel for playing notes
    private Synthesizer synthesizer;
    private MidiChannel channel;

    // Mapping of piano keys to keyboard keys
    private String blackKeys = "WE TYU ";  // Black piano keys mapped to these keys
    private String whiteKeys = "ASDFGHJ";  // White piano keys mapped to these keys
    private String allKeys = "AWSEDFTGYHUJ";  // All piano keys mapped

    // Current octave setting, starting at middle C (5th octave)
    private int octave = 5;
    // Track which keys are being pressed
    private boolean[] keyOn = new boolean[allKeys.length()];
    // Number of keys per octave on a standard piano
    private static final int KEYS_PER_OCTAVE = 12;

    /**
     * Constructor to initialize the virtual piano.
     * It sets up the synthesizer, MIDI channel, and the GUI components like buttons.
     */
    public VirtualPianoView() {
        // Setting the layout manager for this panel
        setLayout(new BorderLayout());

        // Registering this object as a KeyListener
        addKeyListener(this);

        // Initializing the synthesizer for playing MIDI sounds
        startSynthesizer();

        // Creating and setting up the 'Help' button
        JButton helpButton = new JButton("Help");
        // Ensuring the button does not take away focus from key listening
        helpButton.setFocusable(false);
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHelpDialog();
            }
        });
        // Adding the help button at the bottom of the panel
        this.add(helpButton, BorderLayout.SOUTH);
    }

    /**
     * Initializes the MIDI synthesizer and opens a channel for playing notes.
     */
    private void startSynthesizer() {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channel = synthesizer.getChannels()[0];
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Overridden method from JPanel used to handle custom drawing.
     * It draws the visual representation of the piano keys.
     * @param g Graphics context used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Translating the drawing context for proper positioning
        g.translate(50, 50);

        // Displaying the current octave
        g.drawString("Octave: " + octave, 0, -10);

        // Drawing the white keys
        final int WHITE_KEY_WIDTH = 40;
        final int WHITE_KEY_HEIGHT = 100;
        for (int k = 0; k < whiteKeys.length(); k++) {
            g.setColor(keyOn[allKeys.indexOf(whiteKeys.charAt(k))] ? Color.GREEN : Color.WHITE);
            g.fillRect(k * WHITE_KEY_WIDTH, 0, WHITE_KEY_WIDTH, WHITE_KEY_HEIGHT);
            g.setColor(Color.BLACK);
            g.drawRect(k * WHITE_KEY_WIDTH, 0, WHITE_KEY_WIDTH, WHITE_KEY_HEIGHT);
            g.drawString(" " + whiteKeys.charAt(k), k * WHITE_KEY_WIDTH + 10, WHITE_KEY_HEIGHT - 10);
        }

        // Drawing the black keys
        final int BLACK_KEY_WIDTH = WHITE_KEY_WIDTH / 2;
        final int BLACK_KEY_HEIGHT = WHITE_KEY_HEIGHT / 2;
        for (int k = 0; k < blackKeys.length(); k++) {
            if (blackKeys.charAt(k) == ' ') continue;
            int x = (k + 1) * WHITE_KEY_WIDTH - BLACK_KEY_WIDTH / 2;
            g.setColor(keyOn[allKeys.indexOf(blackKeys.charAt(k))] ? Color.GREEN : Color.BLACK);
            g.fillRect(x, 1, BLACK_KEY_WIDTH, BLACK_KEY_HEIGHT);
            g.setColor(Color.WHITE);
            g.drawRect(x, 1, BLACK_KEY_WIDTH, BLACK_KEY_HEIGHT);
            g.drawString(" " + blackKeys.charAt(k), x + 2, BLACK_KEY_HEIGHT - 5);
        }
    }

    /**
     * Displays a help dialog box with information on how to use the virtual piano.
     */
    private void showHelpDialog() {
        JOptionPane.showMessageDialog(this,
            "Virtual Piano Help:\n" +
            "Use the keyboard keys ASDFGHJ for white piano keys.\n" +
            "Use the keyboard keys WE TYU for black piano keys.\n" +
            "Press 0-8 to change octaves, +/- to adjust the current octave.\n" +
            "Press the keys to play notes.",
            "Help", JOptionPane.INFORMATION_MESSAGE);
    }

    // KeyListener methods to handle key events
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this implementation
    }

    /**
     * Handles key press events to play notes and change octaves.
     * @param e KeyEvent associated with the key press.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // Redraw the piano keys to reflect the current state
        repaint();

        // Handling octave change with number keys
        if ("012345678".contains("" + e.getKeyChar())) {
            octave = e.getKeyCode() - 48; // Adjusting ASCII to numerical value
        }

        // Handling octave change with '+' and '-' keys
        if (e.getKeyChar() == '+') {
            octave = Math.min(octave + 1, 8);
        } else if (e.getKeyChar() == '-') {
            octave = Math.max(octave - 1, 0);
        }

        // Playing a note
        int noteIndex = allKeys.indexOf(e.getKeyCode());
        if (noteIndex >= 0 && !keyOn[noteIndex]) {
            keyOn[noteIndex] = true;
            channel.noteOn(octave * KEYS_PER_OCTAVE + noteIndex, 90);
        }
    }

    /**
     * Handles key release events to stop playing notes.
     * @param e KeyEvent associated with the key release.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // Redraw the piano keys to reflect the current state
        repaint();

        // Handling the release of a key
        int noteIndex = allKeys.indexOf(e.getKeyCode());
        if (noteIndex >= 0) {
            keyOn[noteIndex] = false;
            channel.noteOff(octave * KEYS_PER_OCTAVE + noteIndex);
        }
    }

    /**
     * The main method that sets up the GUI and makes the application visible.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VirtualPianoView view = new VirtualPianoView();
                JFrame frame = new JFrame("Virtual Piano");
                // Setting up the frame
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        // Confirm before exiting the application
                        if (JOptionPane.showConfirmDialog(frame,
                            "Are you sure you want to exit?", "Exit Confirmation",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            System.exit(0);
                        }
                    }
                });
                frame.setLocationRelativeTo(null);
                frame.getContentPane().add(view);
                frame.setVisible(true);
                view.requestFocusInWindow(); // Focus to receive key events
            }
        });
    }
}
