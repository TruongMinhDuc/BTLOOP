package uet.oop.bomberman.entities.character.npc;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy{

    int duration = 0;
    public Oneal(int xUnit, int yUnit, Image img, double speed) {
        super(xUnit, yUnit, img, speed);
        loadFrameRight();
        loadFrameLeft();
        loadFrameDie();
    }
    public void loadFrameRight() {
        frameRight[0]  = Sprite.oneal_right1.getFxImage();
        frameRight[1]  = Sprite.oneal_right2.getFxImage();
        frameRight[2]  = Sprite.oneal_right3.getFxImage();
    }
    public void loadFrameLeft() {
        frameLeft[0] = Sprite.oneal_left1.getFxImage();
        frameLeft[1] = Sprite.oneal_left2.getFxImage();
        frameLeft[2] = Sprite.oneal_left3.getFxImage();
    }
    public void loadFrameDie() {
        frameDie[0] = Sprite.oneal_dead.getFxImage();
        frameDie[1] = Sprite.mob_dead1.getFxImage();
        frameDie[2] = Sprite.mob_dead2.getFxImage();
        frameDie[3] = Sprite.mob_dead3.getFxImage();
    }

    @Override
    public int getDirection() {
        return findPaths.chasingPlayer(this, direction);
    }

    @Override
    public void dying() {
        if (duration < 10) {
            this.setImg(frameDie[0]);
            duration++;
        } else if (duration < 20) {
            this.setImg(frameDie[1]);
            duration++;
        } else if (duration < 30) {
            this.setImg(frameDie[2]);
            duration++;
        } else if (duration < 40) {
            this.setImg(frameDie[3]);
            //Board.score += 100;
            BombermanGame.eventHandler.removeEnemyAt(this.x, this.y);
        }

    }
}
