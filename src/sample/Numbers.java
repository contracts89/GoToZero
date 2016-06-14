package sample;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        /*imageView.setFitHeight(74);
        imageView.setFitWidth(104);
        imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));*/
        //imageView.setViewport(new Rectangle2D(0,0,width, height));
        //render();
        //getChildren().addAll(this.imageView);
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
        double startX = 100;
        double startY =-100;
        Path p1 = new Path(new MoveTo(startX, startY));
        p1.getElements().add(new VLineTo(800));
        pathAnimObst1.setPath(p1);
        pathAnimObst1.setDuration(Duration.seconds(4.0d));
        pathAnimObst1.setCycleCount(Timeline.INDEFINITE);
        //pathAnimObst1.setAutoReverse(true);
        pathAnimObst1.play();

        PathTransition pathAnimObst2 = new PathTransition();
        pathAnimObst2.setNode(textLabel2);
        double startX2 = 200;
        double startY2 =-100;
        Path p2 = new Path(new MoveTo(startX2, startY2));
        p2.getElements().add(new VLineTo(800));
        pathAnimObst2.setPath(p2);
        pathAnimObst2.setDuration(Duration.seconds(3.0d));
        pathAnimObst2.setCycleCount(Timeline.INDEFINITE);
        //pathAnimObst1.setAutoReverse(true);
        pathAnimObst2.play();





    }

   /* public void fall() {
        if (isFallingUp) {
            this.offsetY = height * 2;
            this.setTranslateY(this.getTranslateY() + 6);
        } else if (isFallingDown) {
            this.offsetY = height;
            this.setTranslateY(this.getTranslateY() - 6);
        }

        this.columns++;
        this.count++;
        this.offsetX += width;

        if (offsetX == width * 8) {
            offsetX = width;
        }
    }*/

//   public void render() {
//        animation = new SpriteAnimation(this.imageView, Duration.INDEFINITE, count - 1, columns, offsetX, offsetY, width, height);
//        animation.play();
//    }
}