package uet.oop.bomberman;

import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.npc.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class EventHandler {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    //public static final int MAX_LEVEL = 3;
    public static char[][] map = new char[HEIGHT][WIDTH];


    public static int score = 0;

    //public static int countDownTime = 181 * 60;
    //public static int scorePrevious;

    private static List<Entity> entitiesList = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();

    private static List<Enemy> enemyList = new ArrayList<>();

   private double playerSpeed = 0.05;
    private static Bomber player ;
    private MapGen gameLevel;
    private int level;
    //public static File file = new File("res/levels/save.txt");


    public EventHandler() {
        player = new Bomber(1, 1, Sprite.player_right.getFxImage(), playerSpeed);
        //loadLevel();
        gameLevel = new MapGen(this);
    }

    public void changeLevel(int level) {
        entitiesList.clear();
        enemyList.clear();
        stillObjects.clear();
        try {
            gameLevel.createMap(level);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        bombCount = 1;
//        bombRadius = 1;
//        flamePass = false;
//        bombPass = false;
//        wallPass = false;
        Bomb.maxBomb = 1;
        Bomb.blastLength = 1;
        //Bomber.bombPassItem = false;
        //Bomber.brickPass = false;
        //player.setSpeed(playerSpeed);
        this.level = level;
    }

    public Entity getEntity(double x, double y) {
        for (int i = 0; i < entitiesList.size(); i++) {
            Entity temp = entitiesList.get(i);
            if (temp.getX() == x && temp.getY() == y) {
                return temp;
            }
        }
        return null;
    }

    public void removeEntityAt(double x, double y) {
        for (int i = 0; i < entitiesList.size(); i++) {
            Entity tmp = entitiesList.get(i);
            if (tmp != null) {
                if (tmp.getX() == x && tmp.getY() == y) {
                    entitiesList.remove(i);
                    break;
                }
            }
        }
    }

    public void addEntity(Entity object) {
        entitiesList.add(object);
    }

    public void addStillObject(Entity object) {
        stillObjects.add(object);
    }

    public List<Entity> getEntitiesList() {
        return entitiesList;
    }

    public List<Entity> getStillObjects() {
        return stillObjects;
    }

    public static Bomber getPlayer() {
        return player;
    }

//    public int getLeft() {
//        return getPlayer().getHealth();
//    }

    public void setPlayer(Bomber player) {
        this.player = player;
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    //
    public void removeEnemyAt(double x, double y) {
        for (int i = 0; i < enemyList.size(); i++) {
            Enemy temp = enemyList.get(i);
            if (temp.getX() == x && temp.getY() == y) {
                enemyList.remove(temp);
            }
        }
    }

    public void addEnemy(Enemy newEnemy) {
        enemyList.add(newEnemy);
    }

//    public int countDown() {
//        countDownTime--;
//        return countDownTime;
//    }

    public int index(double x, double y) {
        for (int i = 0; i < entitiesList.size(); i++) {
            Entity temp = entitiesList.get(i);
            if (temp.getX() == x && temp.getY() == y) {
                return i;
            }
        }
        return 0;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public MapGen getGameLevel() {
        return this.gameLevel;
    }


    public void update() {
        for (int i = 0; i < entitiesList.size(); i++) {
            entitiesList.get(i).update();
        }
        //System.out.println(getPlayer().getSpeed());
        for (int i = 0; i < enemyList.size(); i++) {
            //System.out.println(enemyList.size());
            enemyList.get(i).update();
        }
    }

    public void render() {
        BombermanGame.gc.clearRect(0, 0, BombermanGame.canvas.getWidth(), BombermanGame.canvas.getHeight());

        stillObjects.forEach(g -> g.render(BombermanGame.gc));
        BombermanGame.eventHandler.getEntitiesList().forEach(g -> g.render(BombermanGame.gc));
        BombermanGame.eventHandler.getEnemyList().forEach(g -> g.render(BombermanGame.gc));
    }
}
