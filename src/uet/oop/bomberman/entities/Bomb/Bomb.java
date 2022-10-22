package uet.oop.bomberman.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventHandler;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.stream.IntStream;

public class Bomb extends Entity {

    protected double timeToExplode = 1000;

    protected int timeExplode = 50;

    protected boolean isExplode = false;

    protected BombRadius[] explosions = new BombRadius[4];

    private int frame = 0;

    public Bomb() {
        removable = true;
    }

    public Bomb(double x, double y, Image img, boolean remove) {
        super(x, y, img);
        removable = remove;
    }

    protected void explosion() {
        isExplode = true;
        for (int i = 0; i < explosions.length; i++) {
            explosions[i] = new BombRadius((int) x, (int) y, i);
        }
    }


    public void updateExplosions() {
        for (BombRadius explosion : explosions) {
            explosion.update(timeExplode);
        }
    }

    @Override
    public void update() {
        if (!removable) {
            if (timeToExplode > 0) {
                timeToExplode--;
                //System.out.println(timeToExplode);
            } else {
                if (!isExplode) {
                    explosion();
                } if (timeExplode > 0) {
                    timeExplode--;
                    updateExplosions();
                } else {
                    removable = true;
                }
            }
        }
        if (!removable && timeExplode == 0) {
            EventHandler.getPlayer().removeBombAt(this.x, this.y);
            System.out.println("remove");
        }
    }

    public void render(GraphicsContext gc) {
        if (!isExplode && !removable) {
            Sprite sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, frame, 60);
            setImg(sprite.getFxImage());
            frame++;
            super.render(gc);
        } else if (!removable) {
            int duration = timeExplode % 30;
            if (duration >= 20) {
                setImg(Sprite.bomb_exploded2.getFxImage());
            } else if (duration >= 10) {
                setImg(Sprite.bomb_exploded1.getFxImage());
            } else {
                setImg(Sprite.bomb_exploded.getFxImage());
            }
            super.render(gc);
            IntStream.range(0, explosions.length).forEach(i -> explosions[i].render(gc));
        }
    }

}
