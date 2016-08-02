package sample.models.playmodels;


import javafx.scene.layout.Pane;
import sample.graphical.PlayerAnimatorImpl;
import sample.graphical.PlayerRendererImpl;
import sample.graphical.interfaces.PlayerAnimator;
import sample.graphical.interfaces.PlayerRenderer;
import sample.models.interfaces.Moveable;
import sample.models.interfaces.Stayable;

import static sample.constants.PlayStateConstants.*;

public class Player extends Pane implements Moveable, Stayable {

    private boolean isMoving;
    private PlayerRenderer playerRenderer;
    private PlayerAnimator playerAnimator;

    public Player() {
        this.playerRenderer = new PlayerRendererImpl();
        this.playerRenderer.render();
        this.playerAnimator = new PlayerAnimatorImpl(this.playerRenderer,this);
        this.playerAnimator.animateOnPlace();
        this.setInitialPosition();
        this.getChildren().addAll(this.playerRenderer.getPlayerImage());
    }

    public boolean isMoving() {
        return this.isMoving;
    }

    private void setMoving(boolean moving) {
        isMoving = moving;
    }

    public PlayerAnimator getAnimator() {
        return this.playerAnimator;
    }

    public void moveRight() {
        this.setMoving(true);
        if (this.getTranslateX() < RIGHT_WALL_BOUNDARY) { //CHECK TO SEE IF RIGHT WALL IS REACHED
            this.setTranslateX(this.getTranslateX() + VELOCITY);
        }
    }

    public void moveLeft() {
        this.setMoving(true);
        if (this.getTranslateX() > LEFT_WALL_BOUNDARY) { //CHECK TO SEE IF LEFT WALL IS REACHED
            this.setTranslateX(this.getTranslateX() - VELOCITY);
        }
    }

    public void stayAtPos() {
        this.setMoving(false);
    }

    private void setInitialPosition() {
        this.setTranslateX(DEFAULT_X_START_POSITION);
        this.setTranslateY(DEFAULT_Y_START_POSITION);
    }
}