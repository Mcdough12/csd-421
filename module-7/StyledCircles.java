import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class StyledCircles extends Application {

    @Override
    public void start(Stage stage) {
        
        // Create circles
        Circle c1 = new Circle(50);
        c1.getStyleClass().add("plaincircle");

        Circle c2 = new Circle(50);
        c2.setId("redcircle");

        Circle c3 = new Circle(50);
        c3.setId("greencircle");

        Circle c4 = new Circle(50);
        c4.getStyleClass().add("plaincircle");
        c4.getStyleClass().add("circleborder");

        // Layout
        HBox root = new HBox(20, c1, c2, c3, c4);
        root.getStyleClass().add("border");

        Scene scene = new Scene(root, 500, 200);

        // Link CSS
        scene.getStylesheets().add("mystyle.css");

        stage.setTitle("Styled Circles Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
