
package sample.graphical;

import javafx.util.Duration;
import sample.animations.SpriteAnimation;
import sample.graphical.interfaces.PlayerAnimator;
import sample.graphical.interfaces.PlayerRenderer;
import sample.models.interfaces.Player;

import static sample.constants.PlayStateConstants.*;

public class PlayerAnimatorImpl implements PlayerAnimator {

    private SpriteAnimation animationOnMove;
    private SpriteAnimation animationOnPlace;
    private Player player;
    private PlayerRenderer renderer;

    public PlayerAnimatorImpl(PlayerRenderer renderer, Player player) {
        this.renderer = renderer;
        this.player = player;
        this.initializeMoveAnimation();
        this.initializeOnPlaceAnimation();
    }

    @Override
    public void animate() {
        if (this.player.isMoving()) {
            this.animationOnMove.play();
        }else{
            this.animationOnPlace.play();
        }
    }

    @Override
    public void stopOnMoveAnimation() {
        this.animationOnMove.stop();
    }

    @Override
    public void stopOnPlaceAnimation() {
        this.animationOnPlace.stop();
    }

    public void stopAllAnimations() {
        this.stopOnMoveAnimation();
        this.stopOnPlaceAnimation();
    }

    private void initializeMoveAnimation() {
        this.animationOnMove = new SpriteAnimation(
                this.renderer.getPlayerImage(),
                Duration.millis(PLAYER_SPRITE_ANIMATION_DURATION),
                PLAYER_SPRITE_RENDERER_INITIAL_OFFSET_X,
                PLAYER_SPRITE_RENDERER_INITIAL_OFFSET_Y);
    }

    private void initializeOnPlaceAnimation() {
        this.animationOnPlace = new SpriteAnimation(
                this.renderer.getPlayerImage(),
                Duration.millis(PLAYER_SPRITE_ANIMATION_DURATION),
                PLAYER_SPRITE_RENDERER_INITIAL_OFFSET_X,
                PLAYER_SPRITE_OFFSETY_ON_PLACE);
    }
}
