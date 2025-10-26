package app;

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

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends Application {
    private static final int CARD_COUNT = 52;
    private final List<ImageView> slots = new ArrayList<>(4);

    @Override public void start(Stage stage) {
        HBox cardRow = new HBox(16);
        cardRow.setAlignment(Pos.CENTER);

        for (int i = 0; i < 4; i++) {
            ImageView iv = new ImageView();
            iv.setPreserveRatio(true);
            iv.setFitWidth(160);
            iv.setSmooth(true);
            slots.add(iv);
        }
        cardRow.getChildren().addAll(slots);

        Button refreshBtn = new Button("Refresh");
        // Lambda expression requirement satisfied here:
        refreshBtn.setOnAction(e -> dealFourDistinctCards());

        VBox root = new VBox(20, cardRow, refreshBtn);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        dealFourDistinctCards();

        stage.setScene(new Scene(root, 800, 400));
        stage.setTitle("Random Four Cards");
        stage.show();
    }

    private void dealFourDistinctCards() {
        List<Integer> deck = new ArrayList<>(CARD_COUNT);
        for (int i = 1; i <= CARD_COUNT; i++) deck.add(i);
        Collections.shuffle(deck);
        List<Integer> four = deck.subList(0, 4);

        for (int i = 0; i < 4; i++) {
            Image img = loadCard(four.get(i));
            slots.get(i).setImage(img); // if img is null (missing file), slot will just be empty
        }
    }

    private Image loadCard(int n) {
        File f = new File("cards/" + n + ".png");
        if (!f.exists()) {
            System.err.println("Missing card image: " + f.getAbsolutePath());
            return null;
        }
        return new Image(f.toURI().toString());
    }

    public static void main(String[] args) { launch(args); }
}
