package sample;

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


    public Label getTextLabel() {
        return textLabel;
    }

    public Number() {
        this.getChildren().addAll(this.textLabel);
        Random randomNumber = new Random();
        int p = randomNumber.nextInt(10) + 1;


        this.textLabel.textProperty().bind(new SimpleIntegerProperty(p).asString());

        this.textLabel.setFont(Font.font("Times New Roman", FontWeight.BLACK, 22));
        this.textLabel.setTextFill(Color.BLUE);



        falling();
    }
    private void falling() {
        PathTransition pathAnimObst1 = new PathTransition();

        pathAnimObst1.setNode(textLabel);

        double startX = Math.random() * 900;
        double startY =-100;
        Path p1 = new Path(new MoveTo(startX, startY));
        p1.getElements().add(new VLineTo(900));
        pathAnimObst1.setPath(p1);
        pathAnimObst1.setDuration(Duration.seconds(Math.random() * 10));
        pathAnimObst1.setCycleCount(1);
        pathAnimObst1.play();

    }
}