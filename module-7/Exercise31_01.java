import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Exercise31_01 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create an HBox container with spacing and padding
        HBox pane = new HBox(15);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20, 20, 20, 20));

        // Create 4 circles (radius 25 each)
        Circle circle1 = new Circle(25);
        Circle circle2 = new Circle(25);
        Circle circle3 = new Circle(25);
        Circle circle4 = new Circle(25);

        // 1. Circle 1 & 2 use the style class: .plaincircle
        circle1.getStyleClass().add("plaincircle");
        circle2.getStyleClass().add("plaincircle");

        // 2. Circle 3 uses the ID: #redcircle
        circle3.setId("redcircle");

        // 3. Circle 4 uses the ID: #greencircle
        circle4.setId("greencircle");

        // Add all circles to the layout pane
        pane.getChildren().addAll(circle1, circle2, circle3, circle4);

        // Create the scene
        Scene scene = new Scene(pane, 400, 200);

        // Add the external stylesheet to the scene/pane
        scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());

        // Configure the stage
        primaryStage.setTitle("Exercise31_01");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Run automated verification test code
        runTestAssertions(circle1, circle2, circle3, circle4, scene);
    }

    /**
     * Test code that ensures style classes, IDs, and stylesheets are applied correctly.
     */
    private void runTestAssertions(Circle c1, Circle c2, Circle c3, Circle c4, Scene scene) {
        System.out.println("=== Running Verification Tests ===");

        // Test 1: Verify stylesheet is loaded
        boolean hasStylesheet = !scene.getStylesheets().isEmpty();
        System.out.println("Test 1 - Stylesheet Attached: " + (hasStylesheet ? "PASS" : "FAIL"));

        // Test 2: Verify class applied to circles 1 and 2
        boolean c1HasClass = c1.getStyleClass().contains("plaincircle");
        boolean c2HasClass = c2.getStyleClass().contains("plaincircle");
        System.out.println("Test 2 - Circle 1 & 2 Style Class: " + (c1HasClass && c2HasClass ? "PASS" : "FAIL"));

        // Test 3: Verify ID applied to circle 3
        boolean c3HasId = "redcircle".equals(c3.getId());
        System.out.println("Test 3 - Circle 3 ID (#redcircle): " + (c3HasId ? "PASS" : "FAIL"));

        // Test 4: Verify ID applied to circle 4
        boolean c4HasId = "greencircle".equals(c4.getId());
        System.out.println("Test 4 - Circle 4 ID (#greencircle): " + (c4HasId ? "PASS" : "FAIL"));

        System.out.println("=== All Tests Completed Successfully ===");
    }

    public static void main(String[] args) {
        launch(args);
    }
}