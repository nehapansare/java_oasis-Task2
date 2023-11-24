import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessingGameGUI extends JFrame {
    private int targetNumber;
    private int attemptsLeft;
    private JTextField guessField;
    private JLabel resultLabel;
    private JLabel attemptsLabel;
    private int points;

    public GuessingGameGUI() {
        setTitle("Guessing Game");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        initializeGame();

        JLabel promptLabel = new JLabel("Enter your guess:");
        guessField = new JTextField(10);
        JButton submitButton = new JButton("Submit Guess");

        resultLabel = new JLabel("");
        attemptsLabel = new JLabel("Attempts left: " + attemptsLeft);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        add(promptLabel);
        add(guessField);
        add(submitButton);
        add(resultLabel);
        add(attemptsLabel);
    }

    private void initializeGame() {
        targetNumber = (int) (Math.random() * 100) + 1;
        attemptsLeft = 5;
        points = 0;
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            if (guess == targetNumber) {
                points += attemptsLeft * 10; // Increase points based on attempts left
                resultLabel.setText("Congratulations! You guessed the correct number. Points: " + points);
                initializeGame(); // Start a new round
            } else {
                attemptsLeft--;
                attemptsLabel.setText("Attempts left: " + attemptsLeft);
                if (attemptsLeft == 0) {
                    resultLabel.setText("Sorry, you've run out of attempts. The correct number was: " + targetNumber);
                    initializeGame(); // Start a new round
                } else {
                    resultLabel.setText("Incorrect guess. Try again!");
                }
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GuessingGameGUI().setVisible(true);
        });
    }
}