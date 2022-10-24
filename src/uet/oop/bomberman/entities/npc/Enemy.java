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

    public boolean checkToMoveUp() {
        double widthFrame = 24;
        double dis = widthFrame / Sprite.SCALED_SIZE;

        int xPos = (int) (x);
        int xPos2 = (int) (x + dis);
        int yPos = (int) (y);
        int yPos2 = (int) (y - speed);

        if (xPos >= 0 && xPos2 < 31 && yPos >= 0 && yPos2 < 13) {
            if (EventHandler.map[yPos][xPos] != ' ' || EventHandler.map[yPos2][xPos2] != ' ') {
                return false;
            }
        }
        return true;
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

        public boolean checkToMoveDown() {
            double widthFrame = 24;

            double dis = widthFrame / Sprite.SCALED_SIZE;
            int xPos = (int) x;
            int xPos2 = (int) (x + dis);

            int yPos = (int) (y + speed);
            int yPos2 = (int) (y + 1);

            if (xPos >= 0 && xPos2 < 31 && yPos >= 0 && yPos2 < 13) {
                if (EventHandler.map[yPos2][xPos] != ' ' || EventHandler.map[yPos2][xPos2] != ' ') {
                    return false;
                }
            }
            return true;
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

            public boolean checkToMoveLeft() {

                double widthFrame = 24;

                double dis = widthFrame / (double) Sprite.SCALED_SIZE;

                int xPos = (int) (x - speed);
                int xPos2 = (int) (x - speed);

                int yPos = (int) y;
                int yPos2 = (int) (y + dis);

                if (xPos >= 0 && xPos2 < 31 && yPos >= 0 && yPos2 < 13) {
                    if (EventHandler.map[yPos][xPos] != ' ' || EventHandler.map[yPos2][xPos] != ' ') {
                        return false;
                    }
                }
                return true;
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

    public boolean checkToMoveRight() {
        double widthFrame = 24;

        double dis = widthFrame / (double) Sprite.SCALED_SIZE;

        int xPos = (int) (x + speed);
        int xPos2 = (int) (x + speed + 1);

        int yPos = (int) y;
        int yPos2 = (int) (y + dis);

        if (xPos >= 0 && xPos2 < 31 && yPos >= 0 && yPos2 < 13) {
            if (EventHandler.map[yPos][xPos2] != ' ' || EventHandler.map[yPos2][xPos2] != ' ') {
                return false;
            }
        }
        //System.out.println("Loi 1" + y + " " + xPos2);
        //System.out.println("Loi 2" + y + " " + xPos2);
        return true;
    }


        public void characterMovement () {
        direction = getDirection();
            switch (direction) {
                case 0:
                    if (checkToMoveUp()) {
                        moveUp(speed);
                    } else {
                        moveUp(0);
                    }
                    break;
                case 1:
                    if (checkToMoveDown()) {
                        moveDown(speed);
                    } else {
                        moveDown(0);
                    }
                    break;
                case 2:
                    if (checkToMoveLeft()) {
                        moveLeft(speed);
                    } else {
                        moveLeft(0);
                    }
                    break;
                case 3:
                    if (checkToMoveRight()) {
                        moveRight(speed);
                    } else {
                        moveRight(0);
                    }
                    break;
            }
        }

        public void update () {
            if (isAlive) {
                characterMovement();
            } else {
                dying();
            }
        }

    }
