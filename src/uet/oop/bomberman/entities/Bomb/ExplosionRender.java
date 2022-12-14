package uet.oop.bomberman.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventHandler;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.eventHandler;

public class ExplosionRender extends Entity {

    protected boolean last;

    public ExplosionRender(double x, double y, int direction, boolean last) {
        this.x = x;
        this.y = y;
        this.last = last;
        switch (direction) {
            case 0:
                img = last ? Sprite.explosion_vertical_top_last2.getFxImage() : Sprite.explosion_vertical2.getFxImage();
                break;
            case 1:
                img = last ? Sprite.explosion_vertical_down_last2.getFxImage() : Sprite.explosion_vertical2.getFxImage();
                break;
            case 2:
                img = last ? Sprite.explosion_horizontal_left_last2.getFxImage() : Sprite.explosion_horizontal2.getFxImage();
                break;
            case 3:
                img = last ? Sprite.explosion_horizontal_right_last2.getFxImage() : Sprite.explosion_horizontal2.getFxImage();
                break;
        }
    }

    public void update(int BlastDirection, int duration) {

        int sizeBomb = eventHandler.getBomber().getBombsList().size();
        for (int i = 0; i < sizeBomb; i++) {
            eventHandler.getBomber().getBombsList().get(i).blastImpactBomb(this);
        }

        int sizeEnemy = eventHandler.getEnemyList().size();
        for (int i = 0; i < sizeEnemy; i++) {
            eventHandler.getEnemyList().get(i).collideWithExplosion(this);
        }
        if (!Bomber.flamePass) {
            eventHandler.getBomber().bomberDie(this);
        }

        duration %= 30;
        if (duration > 10 && duration <= 20) {
            switch (BlastDirection) {
                case 0:
                    img = last ? Sprite.explosion_vertical_top_last1.getFxImage() : Sprite.explosion_vertical1.getFxImage();
                    break;
                case 1:
                    img = last ? Sprite.explosion_vertical_down_last1.getFxImage() : Sprite.explosion_vertical1.getFxImage();
                    break;
                case 2:
                    img = last ? Sprite.explosion_horizontal_left_last1.getFxImage() : Sprite.explosion_horizontal1.getFxImage();
                    break;
                case 3:
                    img = last ? Sprite.explosion_horizontal_right_last1.getFxImage() : Sprite.explosion_horizontal1.getFxImage();
                    break;
            }
        }
        if (duration <= 10) {
            switch (BlastDirection) {
                case 0:
                    img = last ? Sprite.explosion_vertical_top_last.getFxImage() : Sprite.explosion_vertical.getFxImage();
                    break;
                case 1:
                    img = last ? Sprite.explosion_vertical_down_last.getFxImage() : Sprite.explosion_vertical.getFxImage();
                    break;
                case 2:
                    img = last ? Sprite.explosion_horizontal_left_last.getFxImage() : Sprite.explosion_horizontal.getFxImage();
                    break;
                case 3:
                    img = last ? Sprite.explosion_horizontal_right_last.getFxImage() : Sprite.explosion_horizontal.getFxImage();
                    break;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (EventHandler.map[(int) y][(int) x] != 'B') super.render(gc);
    }

    @Override
    public void update() {
    }


}
