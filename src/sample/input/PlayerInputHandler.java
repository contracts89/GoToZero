package sample.input;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import sample.models.playmodels.Player;

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

    public void processSinglePlayerInput() {
        this.processInput();
        if (isPressed(KeyCode.RIGHT)) {
            player.setScaleX(1);
            player.moveRight();
        } else if (isPressed(KeyCode.LEFT)) {
            player.setScaleX(-1);
            player.moveLeft();
        } else {
            player.stayAtPos();
        }
    }
}
