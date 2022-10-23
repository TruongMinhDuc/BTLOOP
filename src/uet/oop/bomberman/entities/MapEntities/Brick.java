package uet.oop.bomberman.entities.MapEntities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.EventHandler;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.EmptyStackException;

public class Brick extends Entity {
    private int Anicount = 130;

    Image[] brickBreakFrame = new Image[3];

    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if(removable && Anicount == 0) {
            EventHandler.map[(int) y][(int) x] = ' ';
            brickBreakFrame[0] = Sprite.brick_exploded.getFxImage();
            brickBreakFrame[1] = Sprite.brick_exploded1.getFxImage();
            brickBreakFrame[2] = Sprite.brick_exploded2.getFxImage();
        }
    }



    public void render(GraphicsContext gc) {
        if(!removable) {
            super.render(gc);
         } else {
            if(Anicount != 0) {
                int duration = Anicount % 50;
                //System.out.println(duration);
                if (duration >= 30) {
                    setImg(Sprite.brick_exploded.getFxImage());
                    super.render(gc);
                    Anicount--;
                } else if (duration >= 10) {
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
