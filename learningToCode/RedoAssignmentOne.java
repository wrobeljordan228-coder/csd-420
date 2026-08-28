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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RedoAssignmentOne extends Application {

    // Array to hold the 4 image views so they can be updated
    private final ImageView[] cardViews = new ImageView[4];

    @Override
    public void start(Stage primaryStage) {
        // 1. Create a horizontal box to hold the 4 cards side-by-side with a 15 pixel gap between each.
        HBox cardLayout = new HBox(15);
        cardLayout.setAlignment(Pos.CENTER);

        // Initialize the ImageView slots and add them to the layout
        for (int i = 0; i < 4; i++) {
            cardViews[i] = new ImageView();
            cardViews[i].setFitHeight(150); // Set a uniform height for the cards
            cardViews[i].setPreserveRatio(true);
            cardLayout.getChildren().add(cardViews[i]);
        }

        // 2. Create a button to deal new cards
        Button refreshButton = new Button("Deal New Cards");
        refreshButton.setOnAction(e -> dealCards());

        // 3. Main vertical layout
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(cardLayout, refreshButton);

        // Draw the initial 4 cards when the app opens
        dealCards();

        // 4. Setup the Stage and Scene
        Scene scene = new Scene(root, 600, 300);
        primaryStage.setTitle("Random Card Generator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void dealCards() {
        // Create a deck of 52 integers representing the cards
        List<Integer> deck = new ArrayList<>();
        for (int i = 1; i <= 52; i++) {
            deck.add(i);
        }

        // Randomize the deck order
        Collections.shuffle(deck);

        // Assign the first 4 shuffled cards to the ImageViews
        for (int i = 0; i < 4; i++) {
            int cardNumber = deck.get(i);
            
            // Build the file path. Assumes images are named 1.png, 2.png... up to 52.png
            String path = "/cards/" + cardNumber + ".png";
            
            try {
                // Load the image from the resources folder
                Image cardImage = new Image(getClass().getResourceAsStream(path));
                cardViews[i].setImage(cardImage);
            } catch (NullPointerException e) {
                System.err.println("Could not find image at path: " + path);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}