package uet.oop.bomberman.entities.npc;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventHandler;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends  Enemy{
    int duration = 0;
    public Balloom(int xUnit, int yUnit, Image img, double speed) {
        super(xUnit, yUnit, img, speed);
        loadFrameRight();
        loadFrameLeft();
        loadFrameDie();
    }
    public void loadFrameRight() {
        frameRight[0] = Sprite.balloom_right1.getFxImage();
        frameRight[1] = Sprite.balloom_right2.getFxImage();
        frameRight[2] = Sprite.balloom_right3.getFxImage();
    }
    public void loadFrameLeft() {
        frameLeft[0] = Sprite.balloom_left1.getFxImage();
        frameLeft[1] = Sprite.balloom_left2.getFxImage();
        frameLeft[2] = Sprite.balloom_left3.getFxImage();
    }
    public void loadFrameDie() {
        frameDie[0] = Sprite.balloom_dead.getFxImage();
        frameDie[1] = Sprite.mob_dead1.getFxImage();
        frameDie[2] = Sprite.mob_dead2.getFxImage();
        frameDie[3] = Sprite.mob_dead3.getFxImage();
    }

    @Override
    public int getDirection() {
        return findPaths.simplePath(this, direction);
    }
    @Override
    public void dying() {
        if (duration < maxFrame) {
            this.setImg(frameDie[0]);
            duration++;
        } else if (duration < maxFrame * 2) {
            this.setImg(frameDie[1]);
            duration++;
        } else if (duration < maxFrame * 3) {
            this.setImg(frameDie[2]);
            duration++;
        } else if (duration < maxFrame * 4) {
            this.setImg(frameDie[3]);
            EventHandler.setScore(EventHandler.getScore() + 100);
            BombermanGame.eventHandler.removeEnemyAt(this.x, this.y);
        }
    }

}
