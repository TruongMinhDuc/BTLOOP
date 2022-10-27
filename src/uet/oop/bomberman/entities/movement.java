package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public abstract class movement extends Entity {
    public int upFrameCount = 0;
    public int downFrameCount = 0;
    public int leftFrameCount = 0;
    public int rightFrameCount = 0;
    public final int maxFrame = 10;
    public static double speed;
    public boolean isAlive = true;

    public movement(int xUnit, int yUnit, Image img, double speed) {
        super(xUnit, yUnit, img);
        this.speed = speed;
    }

    public abstract void characterMovement();

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public abstract void moveUp(double tmpSpeed);
    public abstract void moveDown(double tmpSpeed);
    public abstract void moveLeft(double tmpSpeed);
    public abstract void moveRight(double tmpSpeed);

    @Override
    public void update() {
        characterMovement();
    }
}
