package sample.animations.interfaces;

import javafx.animation.PathTransition;
import javafx.scene.Node;

public interface FallTransition {

    void useFallAnimation(Node node,
                          int duration,
                          int droppoint);

    void reverse();

    PathTransition getPathTransition();
}
