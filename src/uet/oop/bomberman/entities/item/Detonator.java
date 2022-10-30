package uet.oop.bomberman.entities.item;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventHandler;
import uet.oop.bomberman.entities.character.Bomber;

import static uet.oop.bomberman.BombermanGame.eventHandler;


public class Detonator extends Item {
    private boolean obtain = false;

    @Override
    public boolean isObtain() {
        return obtain;
    }
    @Override
    public void setObtain(boolean obtain) {
        this.obtain = obtain;
    }

    public Detonator(double x, double y, Image img) {
        super(x, y, img);
        removable = false;
    }
    public void update() {
        if (obtain) {
            removable = true;
            eventHandler.getBomber().setLife(eventHandler.getBomber().getLife() + 1);
            eventHandler.removeEntityAt(this.x, this.y);
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!removable) {
            super.render(gc);
        }
    }
}
