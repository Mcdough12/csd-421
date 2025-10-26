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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends Application {

    private static final int CARD_COUNT = 52;
    private static final String CARD_PATH_FMT = "/cards/%d.png";

    private final List<ImageView> slots = new ArrayList<>(4);

    @Override
    public void start(Stage stage) {
        // Horizontal strip for cards
        HBox cardRow = new HBox(16);
        cardRow.setAlignment(Pos.CENTER);

        // Prepare 4 image slots
        for (int i = 0; i < 4; i++) {
            ImageView iv = new ImageView();
            iv.setPreserveRatio(true);
            iv.setFitWidth(160);     // adjust to your image size preference
            iv.setSmooth(true);
            slots.add(iv);
        }
        cardRow.getChildren().addAll(slots);

        // Refresh button (Lambda expression used here)
        Button refreshBtn = new Button("Refresh");
        refreshBtn.setOnAction(e -> dealFourDistinctCards());

        VBox root = new VBox(20, cardRow, refreshBtn);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // Initial deal
        dealFourDistinctCards();

        Scene scene = new Scene(root, 800, 400);
        stage.setTitle("Random Four Cards");
        stage.setScene(scene);
        stage.show();
    }

    private void dealFourDistinctCards() {
        // Create a list of 1..52, shuffle, take first 4
        List<Integer> deck = new ArrayList<>(CARD_COUNT);
        for (int i = 1; i <= CARD_COUNT; i++) deck.add(i);
        Collections.shuffle(deck);

        List<Integer> four = deck.subList(0, 4);

        for (int i = 0; i < 4; i++) {
            int cardNum = four.get(i);
            Image img = loadCard(cardNum);
            slots.get(i).setImage(img);
        }
    }

    private Image loadCard(int n) {
        String path = String.format(CARD_PATH_FMT, n);
        InputStream in = getClass().getResourceAsStream(path);
        if (in == null) {
            // Helpful message if resources aren’t placed correctly
            throw new IllegalStateException("Card image not found on classpath: " + path +
                    "\nMake sure 1.png...52.png are in src/main/resources/cards/");
        }
        return new Image(in);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
