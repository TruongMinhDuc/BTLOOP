package uet.oop.bomberman.entities.npc;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventHandler;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {

    int duration = 0;

    public Oneal(int xUnit, int yUnit, Image img, double speed) {
        super(xUnit, yUnit, img, speed);
        loadFrameRight();
        loadFrameLeft();
        loadFrameDie();
    }

    public void loadFrameRight() {
        frameRight.add(Sprite.oneal_right1.getFxImage());
        frameRight.add(Sprite.oneal_right2.getFxImage());
        frameRight.add(Sprite.oneal_right3.getFxImage());
    }

    public void loadFrameLeft() {
        frameLeft.add(Sprite.oneal_left1.getFxImage());
        frameLeft.add(Sprite.oneal_left2.getFxImage());
        frameLeft.add(Sprite.oneal_left3.getFxImage());
    }

    public void loadFrameDie() {
        frameDie.add(Sprite.oneal_dead.getFxImage());
        frameDie.add(Sprite.mob_dead1.getFxImage());
        frameDie.add(Sprite.mob_dead2.getFxImage());
        frameDie.add(Sprite.mob_dead3.getFxImage());
    }

    @Override
    public int getDirection() {
        return findPaths.chasingPlayer(this, direction);
    }

    @Override
    public void dying() {
        if (duration < maxFrame) {
            this.setImg(frameDie.get(0));
            duration++;
        } else if (duration < maxFrame * 2) {
            this.setImg(frameDie.get(1));
            duration++;
        } else if (duration < maxFrame * 3) {
            this.setImg(frameDie.get(2));
            duration++;
        } else if (duration < maxFrame * 4) {
            this.setImg(frameDie.get(3));
            EventHandler.setScore(EventHandler.getScore() + 200);
            BombermanGame.eventHandler.removeEnemyAt(this.x, this.y);
        }

    }
}
