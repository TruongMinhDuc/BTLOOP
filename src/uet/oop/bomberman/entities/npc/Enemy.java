package uet.oop.bomberman.entities.npc;

import javafx.scene.image.Image;
import uet.oop.bomberman.EventHandler;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public abstract class Enemy extends movement {
    List<Image> frameLeft = new ArrayList<>();
    List<Image> frameRight = new ArrayList<>();
    List<Image> frameDie = new ArrayList<>();

    int direction = 2;
    protected FindPaths findPaths = new FindPaths();
    boolean isAlive = true;
    public static double enemySpeed = 0.025;


    public Enemy(int xUnit, int yUnit, Image img, double speed) {
        super(xUnit, yUnit, img, speed);
    }

    public abstract int getDirection();

    public abstract void dying();

    public static double getEnemySpeed() {
        return enemySpeed;
    }

    public static void setEnemySpeed(double enemySpeed) {
        Enemy.enemySpeed = enemySpeed;
    }

    public void moveUp(double tmpSpeed) {
        if (upFrameCount < maxFrame) {
            this.setImg(frameRight.get(0));
            upFrameCount++;
        } else if (upFrameCount < 2 * maxFrame) {
            this.setImg(frameRight.get(1));
            upFrameCount++;
        } else {
            this.setImg(frameRight.get(2));
            upFrameCount++;
            if (upFrameCount == 3 * maxFrame) {
                upFrameCount = 0;
            }
        }
        this.y -= tmpSpeed;
    }

    public void checkToMoveUp() {
        double width = 1;
        int tmpX = (int) (x);
        int tmpX2 = (int) (x + width);

        int tmpY = (int) (y);
        int tmpY2 = (int) (y - speed);

        if (tmpX >= 0 && tmpX2 < 31 && tmpY >= 0 && tmpY2 < 13) {
            if (EventHandler.map[tmpY2][tmpX] != ' ' || EventHandler.map[tmpY2][tmpX2] != ' ') {
                if (EventHandler.map[tmpY2][tmpX] != ' ') {
                    if (this.x - (int) x >= 0.7) {
                        this.x = (int) x + 1;
                    } else {
                        this.y = tmpY2 + width;
                    }
                } else if (EventHandler.map[tmpY2][tmpX2] != ' ') {
                    if (this.x - (int) x <= 0.45) {
                        this.x = tmpX;
                    } else {
                        this.y = tmpY2 + width;
                    }
                }

            }
        }
    }

    public void moveDown(double tmpSpeed) {
        if (downFrameCount < maxFrame) {
            this.setImg(frameLeft.get(0));
            downFrameCount++;
        } else if (downFrameCount < 2 * maxFrame) {
            this.setImg(frameLeft.get(1));
            downFrameCount++;
        } else {
            this.setImg(frameLeft.get(2));
            downFrameCount++;
            if (downFrameCount == 3 * maxFrame) {
                downFrameCount = 0;
            }
        }
        this.y += tmpSpeed;
    }

    public void checkToMoveDown() {
        double width = 1;
        int tmpX = (int) x;
        int tmpX2 = (int) (x + width);

        int tmpY = (int) (y + speed);
        int tmpY2 = (int) (y + 1 + speed);

        if (tmpX >= 0 && tmpX2 < 31 && tmpY >= 0 && tmpY2 < 13) {
            if (EventHandler.map[tmpY2][tmpX] != ' ' || EventHandler.map[tmpY2][tmpX2] != ' ') {
                if (EventHandler.map[(int) (y + 1)][tmpX] != ' ') {
                    if (this.x - (int) x >= 0.7) {
                        this.x = tmpX2;
                    } else {
                        this.y = tmpY;
                    }
                } else if (EventHandler.map[(int) (y + 1)][tmpX2] != ' ') {
                    if (this.x - (int) x <= 0.45) {
                        this.x = tmpX;
                    } else {
                        this.y = tmpY;
                    }
                }
            }
        }
    }

    public void moveLeft(double tmpSpeed) {
        if (leftFrameCount < maxFrame) {
            this.setImg(frameLeft.get(0));
            leftFrameCount++;
        } else if (leftFrameCount < 2 * maxFrame) {
            this.setImg(frameLeft.get(1));
            leftFrameCount++;
        } else {
            this.setImg(frameLeft.get(2));
            leftFrameCount++;
            if (leftFrameCount == 3 * maxFrame) {
                leftFrameCount = 0;
            }
        }
        this.x -= tmpSpeed;
    }

    public void checkToMoveLeft() {
        int width = 1;
        int tmpX = (int) (x - speed);
        int tmpX2 = (int) x + width;

        int tmpY = (int) y;
        int tmpY2 = (int) (y + width);

        if (tmpX >= 0 && tmpX < 31 && tmpY >= 0 && tmpY2 < 13) {
            if (EventHandler.map[tmpY][tmpX] != ' ' || EventHandler.map[tmpY2][tmpX] != ' ') {
                if (EventHandler.map[tmpY][tmpX] != ' ') {
                    if (this.y == (int) y) {
                        this.x = tmpX + 1;
                    } else {
                        if (this.y - (int) y >= 0.7) {
                            this.y = tmpY2;
                        } else {
                            this.x = tmpX2;
                        }
                    }
                } else if (EventHandler.map[(tmpY2)][tmpX] != ' ') {
                    if (this.y - (int) y <= 0.45) {
                        this.y = (int) y;
                    } else {
                        this.x = tmpX2;
                    }
                }
            }
        }
    }

    public void moveRight(double tmpSpeed) {
        if (rightFrameCount < maxFrame) {
            this.setImg(frameRight.get(0));
            rightFrameCount++;
        } else if (rightFrameCount < 2 * maxFrame) {
            this.setImg(frameRight.get(1));
            rightFrameCount++;
        } else {
            this.setImg(frameRight.get(2));
            rightFrameCount++;
            if (rightFrameCount == 3 * maxFrame) {
                rightFrameCount = 0;
            }
        }
        this.x += tmpSpeed;
    }

    public void checkToMoveRight() {
        double width = 1;
        int tmpX = (int) (x + speed);
        int tmpX2 = (int) (x + speed + width);

        int tmpY = (int) y;
        int tmpY2 = (int) (y + width);

        if (tmpX >= 0 && tmpX2 < 31 && tmpY >= 0 && tmpY2 < 13) {
            if (EventHandler.map[tmpY][tmpX2] != ' ' || EventHandler.map[tmpY2][tmpX2] != ' ') {
                if (EventHandler.map[(int) y][tmpX2] != ' ') {
                    if (y == (int) y) {
                        this.x = tmpX2 - width;
                    } else {
                        if (this.y - (int) y >= 0.7) {
                            this.y = tmpY2;
                        } else {
                            this.x = tmpX2 - width;
                        }
                    }
                } else if (EventHandler.map[tmpY2][tmpX2] != 0) {
                    if (this.y - (int) y <= 0.45) {
                        this.y = (int) y;
                    } else {
                        this.x = tmpX2 - width;
                    }
                }
            }
        }
    }


    public void characterMovement() {
        direction = getDirection();
        switch (direction) {
            case 0:
                moveUp(speed);
                checkToMoveUp();
                break;
            case 1:
                moveDown(speed);
                checkToMoveDown();
                break;
            case 2:
                moveLeft(speed);
                checkToMoveLeft();
                break;
            case 3:
                moveRight(speed);
                checkToMoveRight();
                break;
        }
    }

    public void collideWithExplosion(Entity obj) {
        if (isAlive) {
            HashSet<String> maskPlayer1 = blockEntity(this);
            HashSet<String> maskPlayer2 = blockEntity(obj);
            maskPlayer1.retainAll(maskPlayer2);
            if (maskPlayer1.size() > 0) {
                isAlive = false;
            }
        }
    }
    public void update() {
        if (isAlive) {
            characterMovement();
        } else {
            dying();
        }
    }

}
