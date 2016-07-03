package sample.models.menumodels;

import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.constants.Constants;

public class MenuItem extends HBox {

    private TriCircle triCircle1 = new TriCircle(), triCircle2 = new TriCircle();
    private Text text;
    private Runnable script;

    public MenuItem(String name) {
        super(15);
        setAlignment(Pos.CENTER);

        text = new Text(name);
        text.setFont(Constants.MENU_FONT);
        text.setEffect(new GaussianBlur(2));

        getChildren().addAll(triCircle1, text, triCircle2);
        setActive(false);

//               if (name.equals("ONE PLAYER")) {
//            setOnActivate(() -> System.out.println(name + " activated"));
//        } else {
//            setOnActivate(() -> System.out.println(name + " - To be done on next version."));
//        }


    }

    public void setActive(boolean b) {
        triCircle1.setVisible(b);
        triCircle2.setVisible(b);
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
