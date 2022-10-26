package uet.oop.bomberman.entities.item;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventHandler;

public class SpeedItem extends Item{
    private boolean obtain = false;

    @Override
    public boolean isObtain() {
        return obtain;
    }

    @Override
    public void setObtain(boolean obtain) {
        this.obtain = obtain;
    }

    public SpeedItem(double x, double y, Image img) {
        super(x, y, img);
        removable = false;
    }
    public void update() {
        if (obtain) {
            removable = true;
            double playerSpeed =  BombermanGame.eventHandler.getPlayer().getSpeed();
            BombermanGame.eventHandler.getPlayer().setSpeed(playerSpeed + 0.025);
            BombermanGame.eventHandler.removeEntityAt(this.x, this.y);
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!removable) {
            super.render(gc);
        }
    }
}



