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
        if (!playstate.isPaused()) {
            if (isPressed(KeyCode.ESCAPE)) {
                Platform.exit();
            } else if (isPressed(KeyCode.RIGHT)) {
                player.setScaleX(1);
                player.moveRight();
            } else if (isPressed(KeyCode.LEFT)) {
                player.setScaleX(-1);
                player.moveLeft();
            } else if (isPressed(KeyCode.P)) {
                pauseGame(playstate);
            } else {
                player.stayAtPos();
            }
        } else {
            resumeGame(playstate);
        }
    }

    private void pauseGame(PlayState playstate) {
        playstate.setPaused(true);
        playstate.getGameTimer().stop();
        playstate.getStopWatch().stopTimer();
        for (FallingObject fallingObject : playstate.getFallingSymbolsAndNumbers()) {
            fallingObject.getFallTransition().getPathTransition().stop();
        }
        for (MathOperator mathOperator : playstate.getMathOperators()) {
            mathOperator.getFallTransition().getPathTransition().stop();
        }
        playstate.getPlayer().stopAnimation();
    }

    private void resumeGame(PlayState playstate) {
        if (isPressed(KeyCode.P)) {
            playstate.setPaused(false);
            playstate.getGameTimer().start();
            playstate.getStopWatch().resumeTimer();
            playstate.getPlayer().stayAtPos();
            for (FallingObject fallingObject : playstate.getFallingSymbolsAndNumbers()) {
                fallingObject.getFallTransition().getPathTransition().play();
            }
            for (MathOperator mathOperator : playstate.getMathOperators()) {
                mathOperator.getFallTransition().getPathTransition().play();
            }
        }
    }
}
