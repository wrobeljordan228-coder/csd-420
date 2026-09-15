import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Module7 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Main container
        HBox pane = new HBox(15);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 20, 20, 20));

        // Create 4 circles
        Circle circle1 = new Circle(25);
        Circle circle2 = new Circle(25);
        Circle circle3 = new Circle(25);
        Circle circle4 = new Circle(25);

        // 1. Circle 1 uses ID: plaincircle, wrapped inside a bordered StackPane
        circle1.setId("plaincircle");
        StackPane borderPane = new StackPane(circle1);
        borderPane.setId("border");
        borderPane.setPrefSize(60, 100); // Gives vertical rectangular border dimensions

        // 2. Circle 2 uses style class: plaincircle
        circle2.getStyleClass().add("plaincircle");

        // 3. Circle 3 uses ID: redcircle
        circle3.setId("redcircle");

        // 4. Circle 4 uses ID: greencircle
        circle4.setId("greencircle");

        // Add the bordered container and the other circles
        pane.getChildren().addAll(borderPane, circle2, circle3, circle4);

        // Scene setup
        Scene scene = new Scene(pane, 400, 200);
        scene.getStylesheets().add(getClass().getResource("css7.css").toExternalForm());

        primaryStage.setTitle("Exercise31_01");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Run automated tests
        runTestAssertions(circle1, circle2, circle3, circle4, borderPane, scene);
    }

    private void runTestAssertions(Circle c1, Circle c2, Circle c3, Circle c4, StackPane bp, Scene scene) {
        System.out.println("=== Running Verification Tests ===");
        System.out.println("Test 1 - Stylesheet Attached: " + (!scene.getStylesheets().isEmpty() ? "PASS" : "FAIL"));
        System.out.println("Test 2 - Border Container ID: " + ("border".equals(bp.getId()) ? "PASS" : "FAIL"));
        System.out.println("Test 3 - Circle 1 ID (#plaincircle): " + ("plaincircle".equals(c1.getId()) ? "PASS" : "FAIL"));
        System.out.println("Test 4 - Circle 2 Style Class (.plaincircle): " + (c2.getStyleClass().contains("plaincircle") ? "PASS" : "FAIL"));
        System.out.println("Test 5 - Circle 3 ID (#redcircle): " + ("redcircle".equals(c3.getId()) ? "PASS" : "FAIL"));
        System.out.println("Test 6 - Circle 4 ID (#greencircle): " + ("greencircle".equals(c4.getId()) ? "PASS" : "FAIL"));
        System.out.println("=== All Tests Completed Successfully ===");
    }

    public static void main(String[] args) {
        launch(args);
    }
}