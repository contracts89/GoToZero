package sample.models.menumodels;

import javafx.scene.Parent;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

class TriCircle extends Parent {

    private Shape shape1;
    private Shape shape2;
    private Shape shape3;

    public TriCircle() {
        this.shape1 = Shape.subtract(new Circle(5), new Circle(2));
        this.shape1.setFill(Color.WHITE);

        this.shape2 = Shape.subtract(new Circle(5), new Circle(2));
        this.shape2.setFill(Color.WHITE);
        this.shape2.setTranslateX(5);

        this.shape3 = Shape.subtract(new Circle(5), new Circle(2));
        this.shape3.setFill(Color.WHITE);
        this.shape3.setTranslateX(2.5);
        this.shape3.setTranslateY(-5);
        this.getChildren().addAll(shape1, shape2, shape3);

        setEffect(new GaussianBlur(2));
    }
}
