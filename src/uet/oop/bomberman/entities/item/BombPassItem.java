package uet.oop.bomberman.entities.item;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.character.Bomber;

public class BombPassItem extends Item{
    private boolean obtain = false;

    @Override
    public boolean isObtain() {
        return obtain;
    }

    @Override
    public void setObtain(boolean obtain) {
        this.obtain = obtain;
    }

    public BombPassItem(double x, double y, Image img) {
        super(x, y, img);
        removable = false;
    }
    public void update() {
        if (obtain) {
            removable = true;
            Bomber.bombPassItem = true;
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
