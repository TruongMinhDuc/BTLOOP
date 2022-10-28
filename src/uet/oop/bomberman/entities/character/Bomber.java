package uet.oop.bomberman.entities.character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventHandler;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MapEntities.Brick;
import uet.oop.bomberman.entities.item.Item;
import uet.oop.bomberman.entities.item.Portal;
import uet.oop.bomberman.entities.movement;
import uet.oop.bomberman.entities.npc.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static uet.oop.bomberman.BombermanGame.eventHandler;

public class Bomber extends movement {

    public static int left = 3;
    private int dieDuration = 0;
    public static boolean flamePass = false;
    private int health;
    public static boolean brickPass = false;
    public static boolean bombPassItem = false;

    private boolean win = false;
    private boolean lose = false;
    private boolean isDie = false;
    private int upFrameCount = 0;
    private int downFrameCount = 0;
    private int leftFrameCount = 0;
    private int rightFrameCount = 0;

    Image[] frameUp = new Image[3];
    Image[] frameDown = new Image[3];
    Image[] frameLeft = new Image[3];
    Image[] frameRight = new Image[3];

    Image[] frameDie = new Image[3];
    private final int maxFrame = 10;


    private List<Bomb> bombsList = new ArrayList<>();

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setImgUp() {
        // đặt các frame di chuyên vào mảng đặt sẵn
        frameUp[0] = Sprite.player_up.getFxImage();
        frameUp[1] = Sprite.player_up_1.getFxImage();
        frameUp[2] = Sprite.player_up_2.getFxImage();
    }

    public void setImgDown() {
        frameDown[0] = Sprite.player_down.getFxImage();
        frameDown[1] = Sprite.player_down_1.getFxImage();
        frameDown[2] = Sprite.player_down_2.getFxImage();
    }

    public void setImgLeft() {
        frameLeft[0] = Sprite.player_left.getFxImage();
        frameLeft[1] = Sprite.player_left_1.getFxImage();
        frameLeft[2] = Sprite.player_left_2.getFxImage();
    }

    public void setImgRight() {
        frameRight[0] = Sprite.player_right.getFxImage();
        frameRight[1] = Sprite.player_right_1.getFxImage();
        frameRight[2] = Sprite.player_right_2.getFxImage();
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public boolean isLose() {
        return lose;
    }

    public void setLose(boolean lose) {
        this.lose = lose;
    }

    public Bomber(double x, double y, Image img, double speed) {
        super(x, y, img, speed);

        setImgDown();
        setImgUp();
        setImgLeft();
        setImgRight();
        setImgDie();
    }
    public void setImgDie() {
        frameDie[0] = Sprite.player_dead1.getFxImage();
        frameDie[1] = Sprite.player_dead2.getFxImage();
        frameDie[2] = Sprite.player_dead3.getFxImage();
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
            if (brickPass && !bombPassItem) {
                if (EventHandler.map[yPos2][xPos] == '#' || EventHandler.map[yPos2][xPos2] == '#' ||
                        EventHandler.map[yPos2][xPos] == 'B' || EventHandler.map[yPos2][xPos2] == 'B') {
                    return false;
                }
            } else if (!brickPass && bombPassItem) {
                if (EventHandler.map[yPos2][xPos] == '#' || EventHandler.map[yPos2][xPos2] == '#' ||
                        (eventHandler.getEntity(xPos, yPos2) instanceof Brick) || (eventHandler.getEntity(xPos2, yPos2) instanceof Brick)) {
                    return false;
                }
            } else if (!brickPass && !bombPassItem) {
                if (EventHandler.map[yPos2][xPos] == '#' || EventHandler.map[yPos2][xPos2] == '#' ||
                        (eventHandler.getEntity(xPos, yPos2) instanceof Brick) || (eventHandler.getEntity(xPos2, yPos2) instanceof Brick) ||
                        EventHandler.map[yPos2][xPos] == 'B' || EventHandler.map[yPos2][xPos2] == 'B') {
                    return false;
                }
            } else if (brickPass && bombPassItem) {
                if (EventHandler.map[yPos2][xPos] == '#' || EventHandler.map[yPos2][xPos2] == '#') {
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
        int yPos2 = (int) (y + 1);

        if (xPos >= 0 && xPos2 < 31 && yPos >= 0 && yPos2 < 13) {
            if (brickPass && !bombPassItem) {
                if (EventHandler.map[yPos2][xPos] == '#' || EventHandler.map[yPos2][xPos2] == '#' ||
                        EventHandler.map[yPos2][xPos] == 'B' || EventHandler.map[yPos2][xPos2] == 'B') {
                    return false;

                }
            } else if (!brickPass && bombPassItem) {
                if (EventHandler.map[yPos2][xPos] == '#' || EventHandler.map[yPos2][xPos2] == '#' ||
                        (eventHandler.getEntity(xPos, yPos2) instanceof Brick) || (eventHandler.getEntity(xPos2, yPos2) instanceof Brick)) {
                    return false;
                }
            } else if (!brickPass && !bombPassItem) {
                if (EventHandler.map[yPos2][xPos] == '#' || EventHandler.map[yPos2][xPos2] == '#' ||
                        (eventHandler.getEntity(xPos, yPos2) instanceof Brick) || (eventHandler.getEntity(xPos2, yPos2) instanceof Brick) ||
                        EventHandler.map[yPos2][xPos] == 'B' || EventHandler.map[yPos2][xPos2] == 'B') {
                    return false;

                }
            } else if (brickPass && brickPass) {
                if (EventHandler.map[yPos2][xPos] == '#' || EventHandler.map[yPos2][xPos2] == '#') {
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
            if (brickPass && !bombPassItem) {
                if (EventHandler.map[yPos][xPos] == '#' || EventHandler.map[yPos2][xPos] == '#' ||
                        EventHandler.map[yPos2][xPos] == 'B' || EventHandler.map[yPos2][xPos2] == 'B') {

                    return false;

                }
            } else if (!brickPass && bombPassItem) {
                if (EventHandler.map[yPos][xPos] == '#' || EventHandler.map[yPos2][xPos] == '#' ||
                        (eventHandler.getEntity(xPos2, yPos) instanceof Brick) || (eventHandler.getEntity(xPos, yPos2) instanceof Brick)) {

                    return false;

                }
            } else if (!brickPass && !bombPassItem) {
                if (EventHandler.map[yPos][xPos] == '#' || EventHandler.map[yPos2][xPos] == '#' ||
                        (eventHandler.getEntity(xPos2, yPos) instanceof Brick) || (eventHandler.getEntity(xPos, yPos2) instanceof Brick) ||
                        EventHandler.map[yPos2][xPos] == 'B' || EventHandler.map[yPos2][xPos2] == 'B') {
                    return false;
                }
            } else if (brickPass && bombPassItem) {
                if (EventHandler.map[yPos][xPos] == '#' || EventHandler.map[yPos2][xPos] == '#') {
                    return false;
                }
            }
        }
        return true;
    }


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
            if (brickPass && !bombPassItem) {
                if (EventHandler.map[yPos][xPos2] == '#' || EventHandler.map[yPos2][xPos2] == '#' ||
                        EventHandler.map[yPos2][xPos2] == 'B' || EventHandler.map[yPos][xPos2] == 'B') {
                    return false;
                }
            } else if (!brickPass && bombPassItem) {
                if (EventHandler.map[yPos][xPos2] == '#' || EventHandler.map[yPos2][xPos2] == '#' ||
                        (eventHandler.getEntity(xPos2, yPos) instanceof Brick) || (eventHandler.getEntity(xPos2, yPos2) instanceof Brick)) {
                    return false;
                }

            } else if (!brickPass && !bombPassItem) {
                if (EventHandler.map[yPos][xPos2] == '#' || EventHandler.map[yPos2][xPos2] == '#' ||
                        (eventHandler.getEntity(xPos2, yPos) instanceof Brick) || (eventHandler.getEntity(xPos2, yPos2) instanceof Brick) ||
                        EventHandler.map[yPos2][xPos2] == 'B' || EventHandler.map[yPos][xPos2] == 'B') {
                    return false;
                }
            } else if (brickPass && bombPassItem) {
                if (EventHandler.map[yPos][xPos2] == '#' || EventHandler.map[yPos2][xPos2] == '#') {
                    return false;
                }
            }

        }
        return true;
    }


    @Override
    public void update() {
        //int duration = 0;
        impact();
        for (int i = 0; i < bombsList.size(); i++) {
            bombPass(bombsList.get(i));
            bombsList.get(i).update();
        }
        if (isAlive) {
            characterMovement();
            plantBomb();
            //bombsList.forEach(Bomb::update);
        } else {

            if (dieDuration < 10) {
                this.setImg(frameDie[0]);
                dieDuration++;
            } else if (dieDuration < 20) {
                this.setImg(frameDie[1]);
                dieDuration++;
            } else if (dieDuration < 30) {
                this.setImg(frameDie[2]);
                dieDuration++;
            } else if (dieDuration < 40) {
                this.setImg(null);
                dieDuration++;
            } else {
                if (health == 0) {
                    lose = true;
                    eventHandler.removeEntityAt(this.x, this.y);
                } else {
                    this.x = 1;
                    this.y = 1;
                    isDie = true;
                    this.setImg(Sprite.player_right.getFxImage());
                    isAlive = true;
                    dieDuration = 0;
                }
            }
        }
    }

    public void render(GraphicsContext gc) {
        if (!lose) {
            super.render(gc);
        }
        for (Bomb bomb : bombsList) {
            bomb.render(gc);
        }
    }


    public void removeBombAt(double x, double y) {
        for (int i = 0; i < bombsList.size(); i++) {
            Bomb tmp = bombsList.get(i);
            if (tmp.getY() == y && tmp.getX() == x) {
                bombsList.remove(i);
                EventHandler.map[(int) tmp.getY()][(int) tmp.getX()] = ' ';
                break;
            }
        }
    }

    public List<Bomb> getBombsList() {
        return bombsList;
    }

    public void plantBomb() {
        if (BombermanGame.controller.space && bombsList.size() < Bomb.maxBomb) {
            if (!(eventHandler.getEntity(xPosBomb(), yPosBomb()) instanceof Brick)) {
                Bomb bomb = new Bomb(xPosBomb(), yPosBomb(), Sprite.bomb.getFxImage(), false);
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
                break;
            }
        }
        if (check) {
            bombsList.add(bomb);
        }
    }

    public void bombPass(Bomb bomb) {
        if (isAlive) {
            HashSet<String> hideBomb = blockEntity(bomb);
            HashSet<String> hidePlayer = blockEntity(this);
            hideBomb.retainAll(hidePlayer);
            if (hideBomb.size() == 0 && !Bomb.bombPass) {
                EventHandler.map[(int) bomb.getY()][(int) bomb.getX()] = 'B';
            }
        }
    }

    public void impact() {
        for (int i = 0; i < BombermanGame.eventHandler.getEntitiesList().size(); i++) {
            if (BombermanGame.eventHandler.getEntitiesList().get(i) instanceof Item) {
                itemsObtained((Item) BombermanGame.eventHandler.getEntitiesList().get(i));
            }
        }
        for (int i = 0; i < eventHandler.getEnemyList().size(); i++) {
            bomberDie(eventHandler.getEnemyList().get(i));
        }
    }

    public void bomberDie(Entity tmp) {
        if (isAlive && !lose) {
            HashSet<String> maskPlayer1 = blockEntity(this);
            HashSet<String> maskPlayer2 = blockEntity(tmp);
            maskPlayer1.retainAll(maskPlayer2);
            if (maskPlayer1.size() > 0) {
                health--;
                if (health == 0) {
                    setAlive(false);
                } else {
                    setAlive(false);
                }
            }
        }
    }

    public void itemsObtained(Item obj) {
        if (isAlive) {
            HashSet<String> player = blockEntity(this);
            HashSet<String> item = blockEntity(obj);
            player.retainAll(item);
            if (obj instanceof Portal) {
                Portal portal = (Portal) obj;
                if (portal.getActive()) {
                    if (player.size() > 300) {
                        if (eventHandler.getLevel() == 3) {
                            int newLevel = 1;
                            Enemy.setEnemySpeed(Enemy.enemySpeed * 1.25);
                            BombermanGame.eventHandler.setLevel(newLevel);
                            BombermanGame.eventHandler.levelUp(newLevel);
                        } else {
                            int newLevel = BombermanGame.eventHandler.getLevel() + 1;
                            //System.out.println(newLevel);
                            BombermanGame.eventHandler.levelUp(newLevel);
                            BombermanGame.eventHandler.setLevel(newLevel);
                        }
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            this.setImg(Sprite.player_right.getFxImage());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            } else {
                if (!obj.isObtain()) {
                    if (player.size() > 0) {
                        obj.setObtain(true);
                    }
                }
            }
        }
    }
}
