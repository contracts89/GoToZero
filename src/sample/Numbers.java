package sample;

import javafx.animation.PathTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.Random;

public class Numbers extends Pane {
    private Label textLabel = new Label();
    private Label textLabel2 = new Label();

    public Numbers() {
        getChildren().addAll(this.textLabel, this.textLabel2);

        Random randomNumber = new Random();
        int p = randomNumber.nextInt(10) + 1;


        textLabel.textProperty().bind(new SimpleIntegerProperty(p).asString());

        textLabel.setFont(Font.font("Times New Roman", FontWeight.BLACK, 22));
        textLabel.setTextFill(Color.BLUE);

        textLabel2.textProperty().bind(new SimpleIntegerProperty(p+=p).asString());

        textLabel2.setFont(Font.font("Times New Roman", FontWeight.BLACK, 22));
        textLabel2.setTextFill(Color.BLUE);


        falling();
    }
    private void falling() {
        PathTransition pathAnimObst1 = new PathTransition();
        pathAnimObst1.setNode(textLabel);

        Random randomNumber = new Random();
        int p = randomNumber.nextInt(600);
        double startX = p;
        double startY =-100;
        Path p1 = new Path(new MoveTo(startX, startY));
        p1.getElements().add(new VLineTo(800));
        pathAnimObst1.setPath(p1);
        pathAnimObst1.setDuration(Duration.seconds(4.0d));
        pathAnimObst1.setCycleCount(1);
        pathAnimObst1.play();

        PathTransition pathAnimObst2 = new PathTransition();
        pathAnimObst2.setNode(textLabel2);
        Random randomNumber1 = new Random();

        int p3 = randomNumber1.nextInt(600);
        double startX2 = p3;
        double startY2 =-100;
        Path p2 = new Path(new MoveTo(startX2, startY2));
        p2.getElements().add(new VLineTo(800));
        pathAnimObst2.setPath(p2);
        pathAnimObst2.setDuration(Duration.seconds(3.0d));
        pathAnimObst2.setCycleCount(1);
        pathAnimObst2.play();
    }
}