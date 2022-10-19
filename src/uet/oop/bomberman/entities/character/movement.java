package uet.oop.bomberman.entities.character;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public abstract class movement extends Entity {
    public int up = 0;
    public int down = 0;
    public int left = 0;
    public int right = 0;
    public final int frame = 5;
    public double speed;
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
