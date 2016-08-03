package sample.input;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import sample.input.interfaces.PlayerHandler;
import sample.models.interfaces.Fallable;
import sample.models.playmodels.PlayerImpl;
import sample.stages.PlayState;

import java.util.HashMap;

public class PlayerHandlerImpl implements PlayerHandler {

    private HashMap<KeyCode, Boolean> keys;
    private Scene scene;
    private PlayerImpl player;

    public PlayerHandlerImpl(Scene scene, PlayerImpl player) {
        this.scene = scene;
        this.keys = new HashMap<>();
        this.player = player;
    }

    public void processSinglePlayerInput(PlayState playstate) {

        this.processInput();
        if (!playstate.isPaused()) {
            if (isPressed(KeyCode.ESCAPE)) {
                Platform.exit();
            } else if (isPressed(KeyCode.RIGHT)) {
                this.player.setScaleX(1);
                this.player.moveRight();
                this.player.getAnimator().animate();
            } else if (isPressed(KeyCode.LEFT)) {
                this.player.setScaleX(-1);
                this.player.moveLeft();
                this.player.getAnimator().animate();
            } else if (isPressed(KeyCode.P) && !playstate.isPaused()) {
                pauseGame(playstate);
            } else {
                this.player.stayAtPos();
                this.player.getAnimator().stopOnMoveAnimation();
            }
        } else {
            resumeGame(playstate);
        }
    }

    private void pauseGame(PlayState playstate) {
        playstate.getGameTimer().stop();
        playstate.getPlayer().getAnimator().stopAllAnimations();
        for (Fallable fallingObject : playstate.getFallingSymbolsAndNumbers()) {
            fallingObject.getFallTransition().getPathTransition().pause();
        }
        playstate.setPaused(true);
    }

    private void resumeGame(PlayState playstate) {
        if ((isPressed(KeyCode.S)) && playstate.isPaused()) {
            playstate.setPaused(false);
            playstate.getGameTimer().start();
            this.player.getAnimator().animate();
            for (Fallable fallingObject : playstate.getFallingSymbolsAndNumbers()) {
                fallingObject.getFallTransition().getPathTransition().play();
            }
        }
    }

    private void processInput() {
        this.scene.setOnKeyPressed(e -> keys.put(e.getCode(), true));
        this.scene.setOnKeyReleased(e -> keys.put(e.getCode(), false));
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    private boolean isReleased(KeyCode key) {
        return keys.getOrDefault(key, true);
    }
}
