package sample.models.menumodels;

import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.constants.Constants;

public class Item extends HBox {

    private TriCircle triCircle1 = new TriCircle(), triCircle2 = new TriCircle();
    private Text text;
    private Runnable script;

    public Item(String name,String purpose) {
        super(15);
        setAlignment(Pos.CENTER);
        setItemFont(name,purpose);
        getChildren().addAll(triCircle1, text, triCircle2);
        setActive(false);
    }

    public void setItemFont(String name,String purposeOfItem) {
        switch (purposeOfItem) {
            case "CREDITS":
                text = new Text(name);
                text.setFont(Constants.CREDIT_FONT);
                text.setFill(Color.DARKRED);
                break;
            case "MENU":
                text = new Text(name);
                text.setFont(Constants.MENU_FONT);
                text.setEffect(new GaussianBlur(2));
                break;
            case "":
                break;
        }
    }

    public void setActive(boolean b) {
        triCircle1.setVisible(b);
        triCircle2.setVisible(b);
        text.setOnMouseEntered(event -> {
            text.setFill(Color.DARKRED);
        });
        text.setOnMouseExited(event -> {
            text.setFill(Color.GRAY);
        });
        text.setFill(b ? Color.DARKRED : Color.GREY);
    }

    public void setOnActivate(Runnable r) {
        script = r;
    }

    public void activate() {
        if (script != null)
            script.run();
    }
}
