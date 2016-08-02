package sample.models.menumodels;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sample.constants.Constants;

public class ContentFrame extends StackPane {


    public ContentFrame(Node content) {
        this.setAlignment(Pos.CENTER);

        Rectangle frame = new Rectangle(160, 160);
        frame.setArcWidth(30);
        frame.setArcHeight(30);
        frame.setStroke(Color.DARKRED);

        this.getChildren().addAll(frame, content);
    }
    public static Node createMiddleContent() {
        String title = "KEEP RUNNING..";
        HBox letters = new HBox(0);
        letters.setAlignment(Pos.CENTER);
        for (int i = 0; i < title.length(); i++) {
            Text letter = new Text(title.charAt(i) + "");
            letter.setFont(Constants.MENU_FONT);
            letter.setFill(Color.WHITE);
            letters.getChildren().add(letter);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(2), letter);
            tt.setDelay(Duration.millis(i * 50));
            tt.setToY(-25);
            tt.setAutoReverse(true);
            tt.setCycleCount(TranslateTransition.INDEFINITE);
            tt.play();
        }

        return letters;
    }

    public static Node createRightContent() {
        String title = "GO TO ZER0 !";
        HBox letters = new HBox(0);
        letters.setAlignment(Pos.CENTER);
        for (int i = 0; i < title.length(); i++) {
            Text letter = new Text(title.charAt(i) + "");
            letter.setFont(Constants.MENU_FONT);
            letter.setFill(Color.WHITE);
            letter.setOpacity(0);
            letters.getChildren().add(letter);

            FadeTransition ft = new FadeTransition(Duration.seconds(2), letter);
            ft.setDelay(Duration.millis(i * 50));
            ft.setToValue(1);
            ft.setAutoReverse(true);
            ft.setCycleCount(TranslateTransition.INDEFINITE);
            ft.play();
        }

        return letters;
    }
}
