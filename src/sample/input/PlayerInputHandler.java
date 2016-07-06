package sample.input;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import sample.models.playmodels.Number;
import sample.models.playmodels.Player;
import sample.stages.Singleplayer;

import java.util.HashMap;

public class PlayerInputHandler {

    private HashMap<KeyCode, Boolean> keys;
    private Scene scene;
    private Player player;
    private Singleplayer singleplayer;

    public PlayerInputHandler(Scene scene, Player player, Singleplayer singleplayer) {
        this.scene = scene;
        this.keys = new HashMap<>();
        this.player = player;
        this.singleplayer = singleplayer;
    }

    private void processInput() {
        this.scene.setOnKeyPressed(e -> keys.put(e.getCode(), true));
        this.scene.setOnKeyReleased(e -> keys.put(e.getCode(), false));
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }


    public void processSinglePlayerInput() {
        this.processInput();
        if (isPressed(KeyCode.P)) {
            if (!this.singleplayer.isPaused()) {
                this.singleplayer.setPaused(true);
                this.pauseGame();
            } else {
                this.singleplayer.setPaused(false);
                this.resumeGame();
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

    private void resumeGame() {
        this.singleplayer.getAnimationTimer().start();
        this.player.animate();
        this.singleplayer.getStopWatch().resumeTimer();
        for (Number number : this.singleplayer.getNumberList()) {
            number.resumeAnimation();
        }
    }

    private void pauseGame() {
        this.singleplayer.getAnimationTimer().stop();
        this.player.stopAnimation();
        this.singleplayer.getStopWatch().stopTimer();
        for (Number number : this.singleplayer.getNumberList()) {
            number.stopAnimation();
        }
    }
}
