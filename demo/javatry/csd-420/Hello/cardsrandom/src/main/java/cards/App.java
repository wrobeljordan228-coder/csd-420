/**
 * Jordan Wrobel
 * CSD-420
 * Module 1.3
 * Create a program using JavaFX that loads 52 card images and then displays 4 random cards, without repeating any cards. Use Lambda expressions.
 */

package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private final ImageView[] cardShow = new ImageView[4];

    @Override
    public void start(Stage primaryStage) {
        // Horizontal container for the 4 cards
        HBox cardsBank = new HBox(15);
        cardsBank.setAlignment(Pos.CENTER);

        for (int i = 0; i < cardShow.length; i++) {
            cardShow[i] = new ImageView();
            cardShow[i].setFitWidth(110);
            cardShow[i].setFitHeight(160);
            cardShow[i].setPreserveRatio(true);
            cardsBank.getChildren().add(cardShow[i]);
        }

        // Draw first 4 random cards
        newCards();

        // Refresh button with styling
        Button dealButton = new Button("Refresh Cards");
        dealButton.setStyle("-fx-font-size: 14px; -fx-padding: 8 20 8 20; -fx-cursor: hand;");

        // LAMBDA EXPRESSION: Handles button click event
        dealButton.setOnAction(e -> newCards());

        // Main vertical layout
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        root.getChildren().addAll(cardsBank, dealButton);

        // Configure and display the main window
        Scene scene = new Scene(root, 560, 270);
        primaryStage.setTitle("Random 4 Cards Picker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Shuffle 52 card indexes and load 4 unique images.
     */
    private void newCards() {
        List<Integer> deck = new ArrayList<>();
        for (int i = 1; i <= 52; i++) {
            deck.add(i);
        }
        // Randomly shuffle to ensure 4 distinct cards
        Collections.shuffle(deck);

        for (int i = 0; i < 4; i++) {
            int cardNumber = deck.get(i);
            String imagePath = "/cards/" + cardNumber + ".png";
            Image cardImage = new Image(getClass().getResourceAsStream(imagePath));
            cardShow[i].setImage(cardImage);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}