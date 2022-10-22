package uet.oop.bomberman.entities.character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventHandler;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MapEntities.Brick;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.bomb;
import static uet.oop.bomberman.BombermanGame.eventHandler;

public class Bomber extends movement {
    private boolean isDie = false;
    private int upFrameCount = 0;
    private int downFrameCount = 0;
    private int leftFrameCount = 0;
    private int rightFrameCount = 0;

    Image[] frameUp = new Image[3];
    Image[] frameDown = new Image[3];
    Image[] frameLeft = new Image[3];
    Image[] frameRight = new Image[3];

    private final int maxFrame = 10;

    private List<Bomb> bombsList = new ArrayList<>();


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
            if (checkToMoveUp()) {
                moveUp(speed);
            } else {
                moveUp(0);
            }
        }
        if (BombermanGame.controller.down) {
            if (checkToMoveDown()) {
                moveDown(speed);
            } else {
                moveDown(0);
            }
        }
        if (BombermanGame.controller.left) {
            if (checkToMoveLeft()) {
                moveLeft(speed);
            } else {
                moveLeft(0);
            }
        }
        if (BombermanGame.controller.right) {
            if (checkToMoveRight()) {
                moveRight(speed);
            } else {
                moveRight(0);
            }
        }
    }

    @Override
    public void moveUp(double tmpSpeed) {
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
        this.y -= tmpSpeed ;
    }

    public boolean checkToMoveUp() {
        double widthFrame = 24;
        double dis = widthFrame / Sprite.SCALED_SIZE;

        int xPos = (int) (x);
        int xPos2 = (int) (x + dis);
        int yPos = (int) (y);
        int yPos2 = (int) (y - speed);

        if (xPos >= 0 && xPos2 < 31 && yPos >= 0 && yPos2 < 13) {
            if (eventHandler.map[yPos2][xPos] == '#' || eventHandler.map[yPos2][xPos2] == '#' ||
                    eventHandler.map[yPos2][xPos] == '*' || eventHandler.map[yPos2][xPos2] == '*' ||
                    eventHandler.map[yPos2][xPos] == 'B' || eventHandler.map[yPos2][xPos2] == 'B'
            ) {
                if (eventHandler.map[yPos2][xPos] != ' ') {
                    //System.out.println("cho nay la" + eventHandler.map[yPos2][xPos]);
                    return false;
                } else if (eventHandler.map[yPos2][xPos2] != ' ') {
                    //System.out.println("cho nay la" + eventHandler.map[yPos2][xPos]);
                    return false;
                }

            }
        }
        return true;
    }

    @Override
    public void moveDown(double tmpSpeed) {
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
        this.y += tmpSpeed;
    }

    public boolean checkToMoveDown() {
        double widthFrame = 24;

        double dis = widthFrame / Sprite.SCALED_SIZE;
        int xPos = (int) x;
        int xPos2 = (int) (x + dis);

        int yPos = (int) (y + speed);
        int yPos2 = (int) (y + 1 + speed);

        if (xPos >= 0 && xPos2 < 31 && yPos >= 0 && yPos2 < 13) {
            if (eventHandler.map[yPos2][xPos] == '#' || eventHandler.map[yPos2][xPos2] == '#' ||
                    eventHandler.map[yPos2][xPos] == '*' || eventHandler.map[yPos2][xPos2] == '*' ||
                    eventHandler.map[yPos2][xPos] == 'B' || eventHandler.map[yPos2][xPos2] == 'B'
            ) {
                if (eventHandler.map[(int) (y + 1)][xPos] != ' ') {
                    return false;
                } else if (eventHandler.map[(int) (y + 1)][xPos2] != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
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
            if (EventHandler.map[yPos][xPos] == '#' || EventHandler.map[yPos2][xPos] == '#' ||
                    EventHandler.map[yPos][xPos] == '*' || EventHandler.map[yPos2][xPos] == '*' ||
                    EventHandler.map[yPos2][xPos] == 'B' || EventHandler.map[yPos2][xPos2] == 'B'
            ) {
                if (EventHandler.map[yPos][xPos] != ' ') {
                    //System.out.println("loi 1");
                    return false;
                } else if (EventHandler.map[yPos2][xPos] != ' ') {
                    //System.out.println("loi2");

                    return false;
                }
            }
        }
        return true;
    }

    ///
    @Override
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
        int xPos2 = (int) (x + speed + dis);

        int yPos = (int) y;
        int yPos2 = (int) (y + dis);

        if (xPos >= 0 && xPos2 < 31 && yPos >= 0 && yPos2 < 13) {
            if (EventHandler.map[yPos][xPos2] == '#' || EventHandler.map[yPos2][xPos2] == '#' ||
                    EventHandler.map[yPos][xPos2] == '*' || EventHandler.map[yPos2][xPos2] == '*' ||
                    EventHandler.map[yPos2][xPos] == 'B' || EventHandler.map[yPos2][xPos2] == 'B') {
                if (EventHandler.map[yPos][xPos2] != ' ') {
                    //System.out.println("cho nay la (" + eventHandler.map[(int) (y)][xPos2] + ")");
                    return false;
                } else {
                    if(EventHandler.map[yPos2][xPos2]  != ' ') {
                        return  false;
                    }
                }
            }
        }
        //System.out.println("Loi 1" + y + " " + xPos2);
        //System.out.println("Loi 2" + y + " " + xPos2);
        return true;
    }


    @Override
    public void update() {
        if (isAlive) {
            for (int i = 0; i< bombsList.size(); i++) {
                bombPass(bombsList.get(i));
                bombsList.get(i).update();
            }
            characterMovement();
            plantBomb();
            //bombsList.forEach(Bomb::update);
        }
    }

    public void render(GraphicsContext gc) {
        if (!isDie) {
            super.render(gc);
        } else {
            if (eventHandler.countDownTime % 4 == 0 || eventHandler.countDownTime % 4 == 1) {
                super.render(gc);
            }
        }
        for (Bomb bomb : bombsList) {
            bomb.render(gc);
        }
    }

    // có khả năng chạy sinh ra lỗi trong tương lai
    public void removeBombAt(double x, double y) {
        for (int i = 0; i < bombsList.size(); i++) {
            Bomb tmp = bombsList.get(i);
            if (tmp.getY() == y && tmp.getX() == x) {
                bombsList.remove(i);
                eventHandler.map[(int) tmp.getY()][(int) tmp.getX()] = ' ';
                break;
            }
        }
    }

    public void plantBomb() {
        if (BombermanGame.controller.space && bombsList.size() < EventHandler.bombCount) {
            if (!(eventHandler.getEntity(xPosBomb(), yPosBomb()) instanceof Brick)) {
                Bomb bomb = new Bomb(xPosBomb(), yPosBomb(), Sprite.bomb.getFxImage(), false);
                //System.out.println("plant");
                addBomb(bomb);
            }
        }
    }

    public int xPosBomb() {
        if (this.x == (int) this.x) return (int) this.x;
        double difference = this.x - (int) this.x;
        return (difference >= 0.64) ? (int) this.x + 1 : (int) this.x;

    }

    public int yPosBomb() {
        if (this.y == (int) this.y) return (int) this.y;
        double difference = this.y - (int) this.y;
        return (difference >= 0.64) ? (int) this.y + 1 : (int) this.y;
    }

    public void addBomb(Bomb bomb) {
        boolean check = true;
        for (Bomb temp : bombsList) {
            if (temp.getX() == bomb.getX() && temp.getY() == bomb.getY()) {
                check = false;
                //System.out.println("cant");
                break;
            }
        }
        if (check) {
            bombsList.add(bomb);

            //EventHandler.map[yPosBomb()][xPosBomb()] = 'B';
            //System.out.println(1);

        }
    }
    public void bombPass(Bomb bomb) {
        if (isAlive) {
            HashSet<String> hideBomb = blockEntity(bomb);
            HashSet<String> hidePlayer = blockEntity(this);
            hideBomb.retainAll(hidePlayer);
            if (hideBomb.size() == 0 && !EventHandler.bombPass) {
                EventHandler.map[(int) bomb.getY()][(int) bomb.getX()] = 'B';
            }
        }
    }
}
