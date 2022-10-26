package uet.oop.bomberman.entities.npc;

import javafx.scene.image.Image;
import uet.oop.bomberman.EventHandler;
import uet.oop.bomberman.entities.movement;
import uet.oop.bomberman.graphics.Sprite;


public abstract class Enemy extends movement {
    Image[] frameLeft = new Image[3];
    Image[] frameRight = new Image[3];
    Image[] frameDie = new Image[4];

    int direction = 2;
    protected FindPaths findPaths = new FindPaths();
    boolean isAlive = true;


    public Enemy(int xUnit, int yUnit, Image img, double speed) {
        super(xUnit, yUnit, img, speed);
    }

    public abstract int getDirection();

    public abstract void dying();

    public void moveUp(double tmpSpeed) {
        if (upFrameCount < maxFrame) {
            upFrameCount++;
        } else if (upFrameCount < 2 * maxFrame) {
            upFrameCount++;
        } else {
            upFrameCount++;
            if (upFrameCount == 3 * maxFrame) {
                upFrameCount = 0;
            }
        }
        this.y -= tmpSpeed;
    }

    public void checkToMoveUp() {
        double distance = 1;
        int xPos = (int) (x);
        int xPos2 = (int) (x + distance);

        int yPos = (int) (y);
        int yPos2 = (int) (y - speed);

        if (xPos >= 0 && xPos2 < 31 && yPos >= 0 && yPos2 < 13) {
            if (EventHandler.map[yPos2][xPos] != ' ' || EventHandler.map[yPos2][xPos2] != ' ') {
                if (EventHandler.map[yPos2][xPos] != ' ') {
                    if (this.x - (int) x >= 0.7) {
                        this.x = (int) x + 1;
                    } else {
                        this.y = yPos2 + distance;
                    }
                } else if (EventHandler.map[yPos2][xPos2] != ' ') {
                    if (this.x - (int) x <= 0.45) {
                        this.x = xPos;
                    } else {
                        this.y = yPos2 + distance;
                    }
                }

            }
        }
    }

    public void moveDown(double tmpSpeed) {
        if (downFrameCount < maxFrame) {
            downFrameCount++;
        } else if (downFrameCount < 2 * maxFrame) {
            downFrameCount++;
        } else {
            downFrameCount++;
            if (downFrameCount == 3 * maxFrame) {
                downFrameCount = 0;
            }
        }
        this.y += tmpSpeed;
    }

    public void checkToMoveDown() {

        double distance = 1;
        int xPos = (int) x;
        int xPos2 = (int) (x + distance);

        int yPos = (int) (y + speed);
        int yPos2 = (int) (y + 1 + speed);

        if (xPos >= 0 && xPos2 < 31 && yPos >= 0 && yPos2 < 13) {
            if (EventHandler.map[yPos2][xPos] != ' ' || EventHandler.map[yPos2][xPos2] != ' ') {
                if (EventHandler.map[(int) (y + 1)][xPos] != ' ') {
                    if (this.x - (int) x >= 0.7) {
                        this.x = xPos2;
                    } else {
                        this.y = yPos;
                    }
                } else if (EventHandler.map[(int) (y + 1)][xPos2] != ' ') {
                    if (this.x - (int) x <= 0.45) {
                        this.x = xPos;
                    } else {
                        this.y = yPos;
                    }
                }
            }
        }
    }

    public void moveLeft(double tmpSpeed) {
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
        this.x -= tmpSpeed;
    }

    public void checkToMoveLeft() {
        int distance = 1;
        int xPos = (int) (x - speed);
        int xPos2 = (int) x + distance;

        int yPos = (int) y;
        int yPos2 = (int) (y + distance);

        if (xPos >= 0 && xPos < 31 && yPos >= 0 && yPos2 < 13) {
            if (EventHandler.map[yPos][xPos] != ' ' || EventHandler.map[yPos2][xPos] != ' ') {
                if (EventHandler.map[yPos][xPos] != ' ') {
                    if (this.y == (int) y) {
                        this.x = xPos + 1;
                    } else {
                        if (this.y - (int) y >= 0.7) {
                            this.y = yPos2;
                        } else {
                            this.x = xPos2;
                        }
                    }
                } else if (EventHandler.map[(yPos2)][xPos] != ' ') {
                    if (this.y - (int) y <= 0.3) {
                        this.y = (int) y;
                    } else {
                        this.x = xPos2;
                    }
                }
            }
        }
    }

    public void moveRight(double tmpSpeed) {
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
        this.x += tmpSpeed;
    }

    public void checkToMoveRight() {
        double distance = 1;
        int xPos = (int) (x + speed);
        int xPos2 = (int) (x + speed + distance);

        int yPos = (int) y;
        int yPos2 = (int) (y + distance);

        if (xPos >= 0 && xPos2 < 31 && yPos >= 0 && yPos2 < 13) {
            if (EventHandler.map[yPos][xPos2] != ' ' || EventHandler.map[yPos2][xPos2] != ' ') {
                if (EventHandler.map[(int) y][xPos2] != ' ') {
                    if (y == (int) y) {
                        this.x = xPos2 - distance;
                    } else {
                        if (this.y - (int) y >= 0.7) {
                            this.y = yPos2;
                        } else {
                            this.x = xPos2 - distance;
                        }
                    }
                } else if (EventHandler.map[yPos2][xPos2] != 0) {
                    if (this.y - (int) y <= 0.3) {
                        this.y = (int) y;
                    } else {
                        this.x = xPos2 - distance;
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

    public void update() {
        if (isAlive) {
            characterMovement();
        } else {
            dying();
        }
    }

}
