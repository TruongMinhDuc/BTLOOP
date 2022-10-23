package uet.oop.bomberman.entities.MapEntities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventHandler;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.item.Item;
import uet.oop.bomberman.entities.item.Portal;
import uet.oop.bomberman.graphics.Sprite;

import java.util.EmptyStackException;

public class Brick extends Entity {
    private int Anicount = 200;

    private Item hiddenItem = null;
    Image[] brickBreakFrame = new Image[3];

    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }
    public void addEntityBelow(Item entityBelow) {
        this.hiddenItem = entityBelow;
    }

    @Override
    public void update() {

        brickBreakFrame[0] = Sprite.brick_exploded.getFxImage();
        brickBreakFrame[1] = Sprite.brick_exploded1.getFxImage();
        brickBreakFrame[2] = Sprite.brick_exploded2.getFxImage();

        if (removable && Anicount == 0) {
//            BombermanGame.board.removeEntityAt(this.x, this.y);
            if (hiddenItem != null) {
                if (!(hiddenItem instanceof Portal)) {
                    EventHandler.addEntity(hiddenItem);
                    EventHandler.map[(int) this.y][(int) this.x] = ' ';
                    BombermanGame.eventHandler.removeEntityAt(this.x, this.y);
                } else {
                    int index = BombermanGame.eventHandler.index(this.x, this.y);
                    EventHandler.getEntitiesList().set(index, hiddenItem);
//                   BombermanGame.board.addStillObject(entityBelow);
                }
            } else {
                BombermanGame.eventHandler.removeEntityAt(this.x, this.y);
                EventHandler.map[(int) this.y][(int) this.x] = ' ';
            }
        }
    }


    public void render(GraphicsContext gc) {
        if (!removable) {
            super.render(gc);
        } else {
            if (Anicount != 0) {
                int duration = Anicount % 50;
                //System.out.println(duration);
                if (duration >= 35) {
                    setImg(Sprite.brick_exploded.getFxImage());
                    super.render(gc);
                    Anicount--;
                } else if (duration >= 25) {
                    setImg(Sprite.brick_exploded1.getFxImage());
                    super.render(gc);
                    Anicount--;
                } else {
                    setImg(Sprite.brick_exploded2.getFxImage());
                    super.render(gc);
                    Anicount--;
                }
            }
        }
    }
}
