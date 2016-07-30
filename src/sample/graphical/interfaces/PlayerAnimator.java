package sample.graphical.interfaces;

public interface PlayerAnimator extends Animator {

    void animateOnPlace();

    void stopOnMoveAnimation();

    void stopOnPlaceAnimation();

    void stopAllAnimations();
}
