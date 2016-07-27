package sample.interfaces;

import javafx.animation.PathTransition;
import javafx.scene.Node;

public interface FallTransitionImpl {
    void useFallAnimation(Node node,
                          int duration,
                          int droppoint);

    PathTransition getPathTransition();
}
