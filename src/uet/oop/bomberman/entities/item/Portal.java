package uet.oop.bomberman.entities.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventHandler;

public class Portal extends Item{
    private boolean isActive = false;
    public Portal(double x, double y, Image img) {
        super(x, y, img);
        removable = false;
    }
    public boolean getActive() {
        return this.isActive;
    }
    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void update() {
        if(BombermanGame.eventHandler.getEnemyList().size() == 0) {
            isActive = true;
        }
        if (isActive) {
            EventHandler.map[(int) this.y][(int) this.x] = ' ';
        } else {
            EventHandler.map[(int) this.y][(int) this.x] = '#';
        }
    }
}
