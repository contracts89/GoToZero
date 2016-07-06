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


    public void processSinglePlayerInput(PlayState playstate) {

        this.processInput();
        if (isPressed(KeyCode.P)) {
            if (!playstate.isPaused()) {
                playstate.setPaused(true);
                pauseGame(playstate);
            }
        }else if(isPressed(KeyCode.S)){
            if (playstate.isPaused()) {

                playstate.setPaused(false);
                resumeGame(playstate);
            }
        } else if (isPressed(KeyCode.ESCAPE)) {
            Platform.exit();
        } else if (isPressed(KeyCode.RIGHT)) {
            player.setScaleX(1);
            player.moveRight();
        } else if (isPressed(KeyCode.LEFT)) {
            player.setScaleX(-1);
            player.moveLeft();
        } else {
            player.stayAtPos();
        }
    }


    private void pauseGame(PlayState playstate) {
        playstate.getPlayTimer().pause();
        playstate.getPlayer().stopAnimation();
        for (FallingObject fallingObject : playstate.getFallingSymbolsAndNumbers()) {
            fallingObject.getFallTransition().getPathTransition().pause();
        }
        for (MathOperator mathOperator : playstate.getMathOperators()) {
            mathOperator.getFallTransition().getPathTransition().pause();
        }
    }

    private void resumeGame(PlayState playstate) {
        playstate.getPlayTimer().resume();
        playstate.getPlayer().stayAtPos();
        for (FallingObject fallingObject : playstate.getFallingSymbolsAndNumbers()) {
            fallingObject.getFallTransition().getPathTransition().play();
        }
        for (MathOperator mathOperator : playstate.getMathOperators()) {
            mathOperator.getFallTransition().getPathTransition().play();
        }
    }
}
