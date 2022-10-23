package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.EventHandler;

public class Portal extends Item{
    private boolean active = false;
    public Portal(double x, double y, Image img) {
        super(x, y, img);
        removable = false;
    }
    public boolean getActive() {
        return this.active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public void update() {
        if (active) {
            EventHandler.map[(int) this.y][(int) this.x] = ' ';
        } else {
            EventHandler.map[(int) this.y][(int) this.x] = '#';
        }
    }
}
