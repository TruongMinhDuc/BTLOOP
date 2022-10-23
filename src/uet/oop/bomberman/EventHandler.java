package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventHandler {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final int MAX_LEVEL = 3;
    public static char[][] map = new char[HEIGHT][WIDTH];


    public static int score = 0;
    public static boolean flamePass = false;

    public static boolean wallPass = false;
    public static int countDownTime = 181 * 60;
    //public static int scorePrevious;

    private static List<Entity> entitiesList = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();

    //private static List<Enemy> enemies = new ArrayList<>();
    public static double speedOfEnemy = 0.025;
    private double speedOfPlayer = 0.05;
    private static Bomber player;
    private MapGen gameLevel;
    private int level;



    public EventHandler() {
        player = new Bomber(1, 1, Sprite.player_right.getFxImage(), speedOfPlayer);
        //loadLevel();
        gameLevel = new MapGen(this);
    }

//    public void changeLevel(int level) {
//        entities.clear();
//        enemies.clear();
//        stillObjects.clear();
//        try {
//            gameLevel.createMapLevel(level);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        bombCount = 1;
//        bombRadius = 1;
//        flamePass = false;
//        bombPass = false;
//        wallPass = false;
//        this.level = level;
//    }

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
            Entity temp = entitiesList.get(i);
            if (temp.getX() == x && temp.getY() == y) {
                entitiesList.remove(i);
                break;
            }
        }
    }

    public static void addEntity(Entity object) {
        entitiesList.add(object);
    }

    public static void addStillObject(Entity object) {
        stillObjects.add(object);
    }

    public static List<Entity> getEntitiesList() {
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

//    public void setPlayer(Bomber player) {
//        this.player = player;
//    }

//    public List<Enemy> getEnemies() {
//        return enemies;
//    }
//
//    public void removeEnemyAt(double x, double y) {
//        for (int i = 0; i < enemies.size(); i++) {
//            Enemy temp = enemies.get(i);
//            if (temp.getX() == x && temp.getY() == y) {
//                enemies.remove(temp);
//            }
//        }
//    }

//    public void addEnemy(Enemy newEnemy) {
//        enemies.add(newEnemy);
//    }

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
        getEntitiesList().forEach(Entity::update);
    }

    public void render() {
        BombermanGame.gc.clearRect(0, 0, BombermanGame.canvas.getWidth(), BombermanGame.canvas.getHeight());
        stillObjects.forEach(g -> g.render(BombermanGame.gc));
        getEntitiesList().forEach(g -> g.render(BombermanGame.gc));
    }


}
