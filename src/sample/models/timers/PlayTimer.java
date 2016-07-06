package sample.models.timers;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import sample.constants.Constants;

public class PlayTimer extends Timer{
    private Label stopwatch;
    public PlayTimer() {
        super();
        this.setStopwatch(new Label());
    }

    public PlayTimer(long interval, long duration){
        super(interval, duration);
    }

    public Label getStopwatch() {
        return stopwatch;
    }

    public void setStopwatch(Label stopwatch) {
        this.stopwatch = stopwatch;
        this.stopwatch.setTranslateX(5);// X position of Timer
        this.stopwatch.setTranslateY(60); // Y postion of Timer
        this.stopwatch.setFont(Constants.GAME_FONT);
        this.stopwatch.setTextFill(Color.WHITE);
    }

    @Override
    protected void onTick() {
        long elapsedSecondsNow = this.getElapsedTime();
        this.stopwatch.setText(elapsedSecondsNow + " minutes " + elapsedSecondsNow + " seconds");
    }

    @Override
    protected void onFinish() {
        System.out.println("onFinish called!");
    }
}
