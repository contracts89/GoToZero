package sample.models.playmodels;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import sample.constants.Constants;

import java.time.Duration;
import java.time.LocalTime;

public class StopWatch {

    private AnimationTimer timer;
    private Label stopwatch;
    public StopWatch() {
        this.createScene();
        this.setStopwatch(new Label());
    }

    public Label getStopwatch() {
        return this.stopwatch;
    }
    private void setStopwatch(Label stopwatch) {
        this.stopwatch = stopwatch;
        this.stopwatch.setTranslateX(5);// X position of Timer
        this.stopwatch.setTranslateY(60); // Y postion of Timer
        this.stopwatch.setFont(Constants.GAME_FONT);
        this.stopwatch.setTextFill(Color.WHITE);
    }

    public void stopTimer(){
        this.timer.stop();
    }
    public void resumeTimer(){
        this.timer.start();
    }
    public void createScene() {

        BooleanProperty running = new SimpleBooleanProperty(false);

        this.timer = new AnimationTimer() {

            private LocalTime startTime;


            public void handle(long now) {
                long elapsedSeconds = Duration.between(this.startTime, LocalTime.now()).getSeconds();

                long minutes = elapsedSeconds / 60;
                long seconds = elapsedSeconds % 60;

                stopwatch.setText( minutes + " minutes " + seconds + " seconds");

            }

            public void start() {
                running.set(true);
                this.startTime = LocalTime.now();
                super.start();

            }

            public void stop() {
                running.set(false);
                super.stop();
            }
        };
        this.timer.start();

    }
}
