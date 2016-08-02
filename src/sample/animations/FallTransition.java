package sample.animations;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.scene.Node;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.util.Duration;

public class FallTransition implements sample.models.interfaces.FallTransition {

    private PathTransition pathTransition;
    private Path pathAnimation;
    private double startX; // the vertical path lines of falling object
    private double startY;  // start point of falling object

    public FallTransition(double startX, double startY) {
        this.startX = startX;
        this.startY = startY;
        this.pathTransition = new PathTransition();
        this.pathAnimation = new Path(new MoveTo(startX, startY));
    }

    public void reverse() {
        this.pathTransition.setAutoReverse(true);
    }

    public void useFallAnimation(Node node, int duration, int dropPoint) {
        this.pathTransition.setNode(node);
        this.pathAnimation.getElements().add(new VLineTo(dropPoint)); // bottom stop line of the falling object
        this.pathTransition.setPath(this.pathAnimation);
        this.pathTransition.setDuration(Duration.seconds(duration)); //speed of  the falling object
        this.pathTransition.setCycleCount(Animation.INDEFINITE); // set cycle of the falling object to infinity
        this.pathTransition.play();
    }

    public PathTransition getPathTransition() {
        return this.pathTransition;
    }
}
