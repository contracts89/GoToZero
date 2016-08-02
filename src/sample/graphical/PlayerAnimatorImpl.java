<<<<<<< HEAD
package sample.graphical;

import javafx.util.Duration;
import sample.animations.SpriteAnimation;
import sample.graphical.interfaces.PlayerAnimator;
import sample.graphical.interfaces.PlayerRenderer;
import sample.models.playmodels.Player;

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
        }
    }

    @Override
    public void animateOnPlace() {
        if (!this.player.isMoving()) {
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

=======
package sample.graphical;

import javafx.util.Duration;
import sample.animations.SpriteAnimation;
import sample.graphical.interfaces.PlayerAnimator;
import sample.graphical.interfaces.PlayerRenderer;
import sample.models.playmodels.Player;

public class PlayerAnimatorImpl implements PlayerAnimator {

    private SpriteAnimation animationOnMove;
    private SpriteAnimation animationOnPlace;
    private Player player;
    private PlayerRenderer renderer;
    private int count;
    private int columns;
    private int offsetYOnPlace;

    public PlayerAnimatorImpl(PlayerRenderer renderer, Player player) {
        this.renderer = renderer;
        this.player = player;
        this.initializeValues();
        this.initializeMoveAnimation();
        this.initializeOnPlaceAnimation();
    }

    @Override
    public void animate() {
        if (this.player.isMoving()) {
            this.animationOnMove.play();
        }
    }

    @Override
    public void animateOnPlace() {
        if (!this.player.isMoving()) {
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

    private void initializeValues() {
        this.count = 8;
        this.columns = 8;
        this.offsetYOnPlace = 0;
    }

    private void initializeMoveAnimation() {
        this.animationOnMove = new SpriteAnimation(this.renderer.getPlayerImage(),
                Duration.millis(750),
                this.count,
                this.columns,
                this.renderer.getOffsetX(),
                this.renderer.getOffsetY(),
                this.renderer.getWidth(),
                this.renderer.getHeight());
    }

    private void initializeOnPlaceAnimation() {
        this.animationOnPlace = new SpriteAnimation(this.renderer.getPlayerImage(), Duration.millis(750),
                this.count,
                this.columns,
                this.renderer.getOffsetX(),
                this.offsetYOnPlace,
                this.renderer.getWidth(),
                this.renderer.getHeight());
    }
}

>>>>>>> 3fcd8582235d14e95e9f63e689b45d21e27c74fe
