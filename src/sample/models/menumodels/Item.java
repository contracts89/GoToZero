package sample.models.menumodels;

import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Shadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.constants.Constants;

public class Item extends StackPane {

    private Text text;
    private Runnable script;

    public Item(String name, String purpose) {
        super();
        setAlignment(Pos.CENTER);
        setItemFont(name, purpose);
        getChildren().addAll(text);
        setActive(false, purpose);
    }

    public void setItemFont(String name, String purposeOfItem) {
        text = new Text(name);
        switch (purposeOfItem) {
            case "CREDITS":
                text.setFont(Constants.CREDIT_FONT);
                text.setFill(Color.CRIMSON);
                this.setEffect(new GaussianBlur(1));
                break;
            case "MENU":
                text.setFont(Constants.MENU_FONT);
                text.setSmooth(false);
                break;
            case "HELP":
                text.setFont(Constants.HELP_FONT);
                text.setFill(Color.INDIANRED);
                this.setEffect(new Shadow(1,Color.DARKGREY));
                break;
        }
    }

    public void setActive(boolean b, String purpose) {
        if (purpose.equals("MENU")) {
            text.setOnMouseEntered(event -> {
                text.setFill(Color.DARKRED);
            });
            text.setOnMouseExited(event -> {
                text.setFill(Color.GRAY);
            });
            text.setFill(b ? Color.DARKRED : Color.GREY);
        }
    }

    public void setOnActivate(Runnable r) {
        script = r;
    }

    public void activate() {
        if (script != null)
            script.run();
    }
}
