package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import java.awt.*;

public class Item extends Entity {

    protected boolean obtain = false;

    public Item(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    public boolean isObtain() {
        return obtain;
    }

    public void setObtain(boolean obtain) {
        this.obtain = obtain;
    }

}
