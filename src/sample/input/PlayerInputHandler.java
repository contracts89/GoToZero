package sample.input;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import sample.models.playmodels.FallingObject;
import sample.models.playmodels.MathOperator;
import sample.models.playmodels.Player;
import sample.stages.PlayState;

import java.util.HashMap;

public class PlayerInputHandler {

    private HashMap<KeyCode, Boolean> keys;
    private Scene scene;
    private Player player;


    public PlayerInputHandler(Scene scene, Player player) {
        this.scene = scene;
        this.keys = new HashMap<>();
        this.player = player;
    }

    private void processInput() {
        this.scene.setOnKeyPressed(e -> keys.put(e.getCode(), true));
        this.scene.setOnKeyReleased(e -> keys.put(e.getCode(), false));
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
    private boolean isReleassed(KeyCode key) {
        return keys.getOrDefault(key, true);
    }

    public void processSinglePlayerInput(PlayState playstate) {

        this.processInput();
        if (!playstate.isPaused()) {
            if (isPressed(KeyCode.ESCAPE)) {
                Platform.exit();
            } else if (isPressed(KeyCode.RIGHT)) {
                player.setScaleX(1);
                player.moveRight();
            } else if (isPressed(KeyCode.LEFT)) {
                player.setScaleX(-1);
                player.moveLeft();
            } else if (isPressed(KeyCode.P)&& playstate.isPaused() == false) {
                pauseGame(playstate);
            } else {
                player.stayAtPos();
            }
        } else {
            resumeGame(playstate);
        }
    }



    private void pauseGame(PlayState playstate) {

        // added in addition
        playstate.getGameTimer().stop();
        playstate.getPlayer().stopAnimation();

        for (FallingObject fallingObject : playstate.getFallingSymbolsAndNumbers()) {
            fallingObject.getFallTransition().getPathTransition().pause();
        }
        for (MathOperator mathOperator : playstate.getMathOperators()) {
            mathOperator.getFallTransition().getPathTransition().pause();
        }
        playstate.setPaused(true);


        //System.out.println("Pause"); // use for debugging




    }


    private void resumeGame(PlayState playstate) {
        if ((isPressed(KeyCode.S)) && playstate.isPaused()==true){

            playstate.setPaused(false);
            playstate.getGameTimer().start();

            for (FallingObject fallingObject : playstate.getFallingSymbolsAndNumbers()) {
                fallingObject.getFallTransition().getPathTransition().play();
            }
            for (MathOperator mathOperator : playstate.getMathOperators()) {
                mathOperator.getFallTransition().getPathTransition().play();
            }
            //System.out.println("Resume"); // use for debugging
        }
    }
}
