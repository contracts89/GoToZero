package sample;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.VLineTo;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.Random;

public class Number extends StackPane {
    private Label textLabel = new Label();
    private int numberScore;

    public int getNumberScore() {
        return numberScore;
    }

    public void setNumberScore(int numberScore) {
        this.numberScore = numberScore;
    }

    public Label getTextLabel() {
        return textLabel;
    }

    public Number() {
        this.getChildren().addAll(this.textLabel);
        Random randomNumber = new Random();
        int generatedNum = randomNumber.nextInt(10) + 1; // the values of falling numbers (change to double (10) instead of triple (100))

        this.textLabel.textProperty().bind(new SimpleIntegerProperty(generatedNum).asString());

        this.textLabel.setFont(Font.font("Times New Roman", FontWeight.BLACK, 22));
        this.textLabel.setTextFill(Color.WHITESMOKE);

        this.setNumberScore(generatedNum);
        falling();
    }
    private void falling() {
        PathTransition pathAnimObst1 = new PathTransition();

        pathAnimObst1.setNode(textLabel);

        double startX = Math.random() * 900;// the vertical path lines of falling numbers
        double startY =-100;// srart point of falling numbers

        Path pathAnimation = new Path(new MoveTo(startX, startY));
        pathAnimation.getElements().add(new VLineTo(900)); // bottom stop line of the falling numbers
        pathAnimObst1.setPath(pathAnimation);
        pathAnimObst1.setDuration(Duration.seconds(10)); //speed of falling numbers (change to constant 10 seconds, before was Math.random() * 10)
        pathAnimObst1.setCycleCount(Animation.INDEFINITE); // set cycle of falling numbers to infinity
        pathAnimObst1.play();

    }

}