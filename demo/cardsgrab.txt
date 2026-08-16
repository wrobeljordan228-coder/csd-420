import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;

// Main Application Class
public class cardsgrab extends Application {
    private final HBox cardPane = new HBox(15);

    @Override
    public void start(Stage primaryStage) {
        cardPane.setAlignment(Pos.CENTER);
        Button btnRefresh = new Button("Refresh");
        btnRefresh.setOnAction(e -> refreshCards());
        
        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(cardPane);
        mainPane.setBottom(new HBox(btnRefresh));
        
        refreshCards();
        primaryStage.setScene(new Scene(mainPane, 450, 250));
        primaryStage.show();
    }

    // Method to shuffle deck and display 4 images
    private void refreshCards() {
        cardPane.getChildren().clear();
        ArrayList<Integer> deck = new ArrayList<>();
        for (int i = 1; i <= 52; i++) deck.add(i);
        Collections.shuffle(deck);
        
        for (int i = 0; i < 4; i++) {
            Image img = new Image(getClass().getResourceAsStream("cards/" + deck.get(i) + ".png"));
            ImageView iv = new ImageView(img);
            iv.setFitWidth(80);
            iv.setPreserveRatio(true);
            cardPane.getChildren().add(iv);
        }
    }
    public static void main(String[] args) { launch(args); }
}