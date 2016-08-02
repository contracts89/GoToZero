package sample.models.interfaces;

import javafx.animation.PathTransition;
import javafx.scene.Node;

public interface FallTransition {
    void useFallAnimation(Node node,
                          int duration,
                          int droppoint);

    PathTransition getPathTransition();
}
