package uet.oop.bomberman;

import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.npc.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class EventHandler {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static char[][] map = new char[HEIGHT][WIDTH];


    private static int score = 0;


    private static List<Entity> entitiesList = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();

    private static List<Enemy> enemyList = new ArrayList<>();

    private double playerSpeed = 0.05;
    private static Bomber player;
    private MapGen game;
    private int level;
    private int showLevel;


    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        EventHandler.score = score;
    }

    public int getShowLevel() {
        return showLevel;
    }

    public void setShowLevel(int showLevel) {
        this.showLevel = showLevel;
    }

    public EventHandler() {
        player = new Bomber(1, 1, Sprite.player_right.getFxImage(), playerSpeed);
        game = new MapGen(this);
    }

    public void levelUp(int level) {
        entitiesList.clear();
        enemyList.clear();
        player.clearBombList();
        stillObjects.clear();
        BombermanGame.stateScene();
        try {
            game.createMap(level);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
    public void addEntity(Entity object) {
        entitiesList.add(object);
    }
    public List<Entity> getEntitiesList() {
        return entitiesList;
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



    public void addStillObject(Entity object) {
        stillObjects.add(object);
    }

    public List<Entity> getStillObjects() {
        return stillObjects;
    }

    public  Bomber getPlayer() {
        return player;
    }

    public void setPlayer(Bomber player) {
        this.player = player;
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

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
        return this.game;
    }


    public void update() {
        for (int i = 0; i < entitiesList.size(); i++) {
            entitiesList.get(i).update();
        }
        for (int i = 0; i < enemyList.size(); i++) {
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
