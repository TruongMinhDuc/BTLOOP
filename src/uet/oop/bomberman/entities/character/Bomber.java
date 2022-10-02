package uet.oop.bomberman.entities.character;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;

public class Bomber extends movement {
    private int upFrameCount = 0;
    private int downFrameCount = 0;
    private int leftFrameCount = 0;
    private int rightFrameCount = 0;

    Image[] frameUp = new Image[3];
    ;
    Image[] frameDown = new Image[3];
    ;
    Image[] frameLeft = new Image[3];
    ;
    Image[] frameRight = new Image[3];
    ;

    private final int maxFrame = 5;


    public void setFrameUp() {
        // đặt các frame di chuyên vào mảng đặt sẵn
        frameUp[0] = Sprite.player_up.getFxImage();
        frameUp[1] = Sprite.player_up_1.getFxImage();
        frameUp[2] = Sprite.player_up_2.getFxImage();
    }

    public void setFrameDown() {
        frameDown[0] = Sprite.player_down.getFxImage();
        frameDown[1] = Sprite.player_down_1.getFxImage();
        frameDown[2] = Sprite.player_down_2.getFxImage();
    }

    public void setFrameLeft() {
        frameLeft[0] = Sprite.player_left.getFxImage();
        frameLeft[1] = Sprite.player_left_1.getFxImage();
        frameLeft[2] = Sprite.player_left_2.getFxImage();
    }

    public void setFrameRight() {
        frameRight[0] = Sprite.player_right.getFxImage();
        frameRight[1] = Sprite.player_right_1.getFxImage();
        frameRight[2] = Sprite.player_right_2.getFxImage();
    }

    public Bomber(int x, int y, Image img, double speed) {
        super(x, y, img, speed);
        setFrameDown();
        setFrameUp();
        setFrameLeft();
        setFrameRight();
    }

    @Override
    public void characterMovement() {
        if (BombermanGame.controller.up) {
            System.out.println("1");
            moveUp();
        }
        if (BombermanGame.controller.down) {
            System.out.println("2");
            moveDown();
        }
        if (BombermanGame.controller.left) {
            System.out.println("3");
            moveLeft();
        }
        if (BombermanGame.controller.right) {
            System.out.println("4");
            moveRight();
        }
    }

    @Override
    public void moveUp() {
        if (upFrameCount < maxFrame) {
            this.setImg(frameUp[0]);
            upFrameCount++;
        } else if (upFrameCount < 2 * maxFrame) {
            this.setImg(frameUp[1]);
            upFrameCount++;
        } else {
            this.setImg(frameUp[2]);
            upFrameCount++;
            if (upFrameCount == 3 * maxFrame) {
                upFrameCount = 0;
            }
        }
        this.y -= speed;
    }

    @Override
    public void moveDown() {
        if (downFrameCount < maxFrame) {
            this.setImg(frameDown[0]);
            downFrameCount++;
        } else if (downFrameCount < 2 * maxFrame) {
            this.setImg(frameDown[1]);
            downFrameCount++;
        } else {
            this.setImg(frameDown[2]);
            downFrameCount++;
            if (downFrameCount == 3 * maxFrame) {
                downFrameCount = 0;
            }
        }
        this.y += speed;
    }

    @Override
    public void moveLeft() {
        if (leftFrameCount < maxFrame) {
            this.setImg(frameLeft[0]);
            leftFrameCount++;
        } else if (leftFrameCount < 2 * maxFrame) {
            this.setImg(frameLeft[1]);
            leftFrameCount++;
        } else {
            this.setImg(frameLeft[2]);
            leftFrameCount++;
            if (leftFrameCount == 3 * maxFrame) {
                leftFrameCount = 0;
            }
        }
        this.x -= speed;
    }

    @Override
    public void moveRight() {
        if (rightFrameCount < maxFrame) {
            this.setImg(frameRight[0]);
            rightFrameCount++;
        } else if (rightFrameCount < 2 * maxFrame) {
            this.setImg(frameRight[1]);
            rightFrameCount++;
        } else {
            this.setImg(frameRight[2]);
            rightFrameCount++;
            if (rightFrameCount == 3 * maxFrame) {
                rightFrameCount = 0;
            }
        }
        this.x += speed;
    }

    @Override
    public void update() {
        if (isAlive) {
            characterMovement();
        }
    }


}
