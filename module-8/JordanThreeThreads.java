import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class JordanThreeThreads extends Application {

    private static final int TARGET_COUNT = 10000;
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%&*";

    private final Random random = new Random();
    private TextArea textArea;
    private Label statusLabel;
    private Button startButton;

    // Thread completion trackers for UI status
    private final AtomicInteger completedThreads = new AtomicInteger(0);

    @Override
    public void start(Stage primaryStage) {
        // Run automated method tests prior to UI display
        runUnitTests();

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setEditable(false);

        startButton = new Button("Start Threads");
        statusLabel = new Label("Ready to start. Target: " + TARGET_COUNT + " per thread (30,000 total).");

        HBox topBar = new HBox(10, startButton, statusLabel);
        topBar.setPadding(new Insets(0, 0, 10, 0));

        startButton.setOnAction(e -> startThreads());

        root.setTop(topBar);
        root.setCenter(new ScrollPane(textArea));

        Scene scene = new Scene(root, 700, 500);
        primaryStage.setTitle("Jordan Three Threads Generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Spawns and starts the three concurrent character-generating threads.
     */
    private void startThreads() {
        textArea.clear();
        completedThreads.set(0);
        startButton.setDisable(true);
        statusLabel.setText("Running threads...");

        // Thread 1: Random letter characters
        Thread letterThread = new Thread(() -> {
            for (int i = 0; i < TARGET_COUNT; i++) {
                char ch = getRandomLetter();
                appendCharacterToTextArea(ch);
            }
            onThreadFinished("Letters");
        });

        // Thread 2: Random number digits
        Thread digitThread = new Thread(() -> {
            for (int i = 0; i < TARGET_COUNT; i++) {
                char ch = getRandomDigit();
                appendCharacterToTextArea(ch);
            }
            onThreadFinished("Digits");
        });

        // Thread 3: Random special characters
        Thread specialCharThread = new Thread(() -> {
            for (int i = 0; i < TARGET_COUNT; i++) {
                char ch = getRandomSpecialChar();
                appendCharacterToTextArea(ch);
            }
            onThreadFinished("Special Characters");
        });

        // Start all three threads concurrently
        letterThread.start();
        digitThread.start();
        specialCharThread.start();
    }

    /**
     * Appends an individual character immediately on the JavaFX Application Thread.
     */
    private void appendCharacterToTextArea(char ch) {
        Platform.runLater(() -> textArea.appendText(String.valueOf(ch)));
    }

    /**
     * Updates completion state and re-enables UI controls once all 3 threads finish.
     */
    private void onThreadFinished(String threadType) {
        int finished = completedThreads.incrementAndGet();
        Platform.runLater(() -> {
            if (finished == 3) {
                statusLabel.setText("Completed: All 3 threads finished (" + (TARGET_COUNT * 3) + " total characters).");
                startButton.setDisable(false);
            } else {
                statusLabel.setText("Finished: " + threadType + " (" + finished + "/3 threads complete)");
            }
        });
    }

    // --- Helper Random Generator Methods ---

    public char getRandomLetter() {
        return LETTERS.charAt(random.nextInt(LETTERS.length()));
    }

    public char getRandomDigit() {
        return DIGITS.charAt(random.nextInt(DIGITS.length()));
    }

    public char getRandomSpecialChar() {
        return SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length()));
    }

    // --- Automated Unit Test Suite ---

    /**
     * Tests that all character generator methods output valid characters
     * and that character counts adhere to specifications.
     */
    private void runUnitTests() {
        System.out.println("=== Starting Unit Tests for JordanThreeThreads ===");

        // Test 1: Random Letter Generation
        boolean letterPass = true;
        for (int i = 0; i < 500; i++) {
            char c = getRandomLetter();
            if (!LETTERS.contains(String.valueOf(c))) {
                letterPass = false;
                break;
            }
        }
        System.out.println("Test 1 - Random Letters Validation: " + (letterPass ? "PASS" : "FAIL"));

        // Test 2: Random Digit Generation
        boolean digitPass = true;
        for (int i = 0; i < 500; i++) {
            char c = getRandomDigit();
            if (!DIGITS.contains(String.valueOf(c))) {
                digitPass = false;
                break;
            }
        }
        System.out.println("Test 2 - Random Digits Validation: " + (digitPass ? "PASS" : "FAIL"));

        // Test 3: Random Special Characters Generation
        boolean specialPass = true;
        for (int i = 0; i < 500; i++) {
            char c = getRandomSpecialChar();
            if (!SPECIAL_CHARS.contains(String.valueOf(c))) {
                specialPass = false;
                break;
            }
        }
        System.out.println("Test 3 - Random Special Chars Validation: " + (specialPass ? "PASS" : "FAIL"));

        // Test 4: Verify Target Count Minimum
        boolean targetPass = TARGET_COUNT >= 10000;
        System.out.println("Test 4 - Minimum 10,000 Generation Target: " + (targetPass ? "PASS" : "FAIL"));

        System.out.println("=== Unit Tests Completed Successfully ===\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}