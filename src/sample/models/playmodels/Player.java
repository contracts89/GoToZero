package sample.models.playmodels;


import javafx.scene.layout.Pane;
import sample.constants.Constants;
import sample.graphical.*;
import sample.graphical.interfaces.PlayerAnimator;
import sample.graphical.interfaces.PlayerRenderer;
import sample.interfaces.Moveable;
import sample.interfaces.Stayable;

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
        if (this.getTranslateX() < Constants.RIGHT_WALL_BOUNDARY) { //CHECK TO SEE IF RIGHT WALL IS REACHED
            this.setTranslateX(this.getTranslateX() + Constants.VELOCITY);
        }
    }

    public void moveLeft() {
        this.setMoving(true);
        if (this.getTranslateX() > Constants.LEFT_WALL_BOUNDARY) { //CHECK TO SEE IF LEFT WALL IS REACHED
            this.setTranslateX(this.getTranslateX() - Constants.VELOCITY);
        }
    }

    public void stayAtPos() {
        this.setMoving(false);
    }

    private void setInitialPosition() {
        this.setTranslateX(Constants.DEFAULT_X_START_POSITION);
        this.setTranslateY(Constants.DEFAULT_Y_START_POSITION);
    }
}