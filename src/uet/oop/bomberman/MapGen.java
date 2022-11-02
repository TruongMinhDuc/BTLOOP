package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MapEntities.Brick;
import uet.oop.bomberman.entities.MapEntities.Grass;
import uet.oop.bomberman.entities.MapEntities.Wall;
import uet.oop.bomberman.entities.item.*;
import uet.oop.bomberman.entities.npc.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TODO: đọc từ file các kí tự text thêm vào obj
public class MapGen {
    private EventHandler eventHandler;
    public MapGen(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
        //public static int level = 1;
    }
    //public static char[][] map = new char[BombermanGame.HEIGHT][BombermanGame.WIDTH];

    public void createMap(int level) throws FileNotFoundException {
        //mapExample
//        for (int i = 0; i < BombermanGame.WIDTH; i++) {
//            for (int j = 0; j < BombermanGame.HEIGHT; j++) {
//                Entity object;
//                if (j == 0 || j == BombermanGame.HEIGHT - 1 || i == 0 || i == BombermanGame.WIDTH - 1) {
//                    object = new Wall(i, j, Sprite.wall.getFxImage());
//                }
//                else {
//                    object = new Grass(i, j, Sprite.grass.getFxImage());
//                }
//                BombermanGame.stillObjects.add(object);
//            }
//        }

        String fileName = "Level" + level + ".txt";
        File file = new File("res/levels/" + fileName);
        Scanner sc = new Scanner(file);
        //map information
//        int mapLevel = sc.nextInt();
//        int mapHeight = sc.nextInt();
//        int mapWidth = sc.nextInt();


        //map load
        int he = 0;
        while (sc.hasNextLine()) {
            String tmp = sc.nextLine();
            //System.out.println(tmp.length());
            //System.out.println(row);
            for (int j = 0; j < tmp.length(); j++) {
                BombermanGame.eventHandler.map[he][j] = tmp.charAt(j);
            }
            he++;
        }
        //System.out.println(BombermanGame.map[][16]);

        for (int i = 0; i < BombermanGame.HEIGHT; i++) {
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                if (EventHandler.map[i][j] == '#') {
                    Entity obj;
                    obj = new Wall(j, i, Sprite.wall.getFxImage());
                    BombermanGame.eventHandler.addStillObject(obj);
                    //EventHandler.addEntity(obj);
                } else {
                    Entity obj;
                    obj = new Grass(j, i, Sprite.grass.getFxImage());
                    BombermanGame.eventHandler.addStillObject(obj);
                    //EventHandler.addEntity(obj);
                }
                switch (EventHandler.map[i][j]) {
                    case '*':
                        Brick brick = new Brick(j, i, Sprite.brick.getFxImage());
                        BombermanGame.eventHandler.addEntity(brick);
                        break;

                    case 's':
                        Brick brickCoverS = new Brick(j, i, Sprite.brick.getFxImage());
                        SpeedItem hiddenItem = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage());
                        BombermanGame.eventHandler.addEntity(brickCoverS);
                        brickCoverS.addEntityBelow(hiddenItem);
                        break;

                    case 'x':
                        Brick brickCoverX = new Brick(j, i, Sprite.brick.getFxImage());
                        Portal portal = new Portal(j, i, Sprite.portal.getFxImage());
                        BombermanGame.eventHandler.addEntity(brickCoverX);
                        brickCoverX.addEntityBelow(portal);
                        break;
                    case 'w':
                        Brick brickCoverW = new Brick(j, i, Sprite.brick.getFxImage());
                        WallPass wallPass = new WallPass(j, i, Sprite.powerup_wallpass.getFxImage());
                        BombermanGame.eventHandler.addEntity(brickCoverW);
                        brickCoverW.addEntityBelow(wallPass);
                        break;
                    case 'i':
                        Brick brickCoverI = new Brick(j, i, Sprite.brick.getFxImage());
                        BombPassItem bombPassItem = new BombPassItem(j, i, Sprite.powerup_bombpass.getFxImage());
                        BombermanGame.eventHandler.addEntity(brickCoverI);
                        brickCoverI.addEntityBelow(bombPassItem);
                        break;
                    case 'f':
                        Brick brickCoverF = new Brick(j, i, Sprite.brick.getFxImage());
                        FlameUp flameUp = new FlameUp(j, i, Sprite.powerup_flames.getFxImage());
                        BombermanGame.eventHandler.addEntity(brickCoverF);
                        brickCoverF.addEntityBelow(flameUp);
                        break;
                    case 'b':
                        Brick brickCoverB = new Brick(j, i, Sprite.brick.getFxImage());
                        BombPluss bombPluss = new BombPluss(j, i, Sprite.powerup_bombs.getFxImage());
                        BombermanGame.eventHandler.addEntity(brickCoverB);
                        brickCoverB.addEntityBelow(bombPluss);
                        break;
                    case 'u':
                        Brick brickCoverU = new Brick(j, i, Sprite.brick.getFxImage());
                        FlamePass flamePass = new FlamePass(j, i, Sprite.powerup_flamepass.getFxImage());
                        BombermanGame.eventHandler.addEntity(brickCoverU);
                        brickCoverU.addEntityBelow(flamePass);
                        break;
                    case 'd':
                        Brick brickCoverD = new Brick(j, i, Sprite.brick.getFxImage());
                        Detonator detonator = new Detonator(j, i, Sprite.powerup_detonator.getFxImage());
                        BombermanGame.eventHandler.addEntity(brickCoverD);
                        brickCoverD.addEntityBelow(detonator);
                        break;
                    case '1':
                        EventHandler.map[i][j] = ' ';
                        Enemy balloom = new Balloom(j, i, Sprite.balloom_left1.getFxImage(), Enemy.enemySpeed);
                        BombermanGame.eventHandler.addEnemy(balloom);
                        break;
                    case '2':
                        EventHandler.map[i][j] = ' ';
                        Enemy oneal = new Oneal(j, i, Sprite.oneal_left1.getFxImage(), Enemy.enemySpeed);
                        BombermanGame.eventHandler.addEnemy(oneal);
                        break;
                    case '3':
                        EventHandler.map[i][j] = ' ';
                        Enemy konodoira = new Konodoira(j, i, Sprite.oneal_left1.getFxImage(), Enemy.enemySpeed);
                        BombermanGame.eventHandler.addEnemy(konodoira);
                        break;
                    case '4':
                        EventHandler.map[i][j] = ' ';
                        Enemy doll = new Doll(j, i, Sprite.doll_left1.getFxImage(),Enemy.enemySpeed);
                        BombermanGame.eventHandler.addEnemy(doll);
                        break;

                }
            }
        }

        eventHandler.getBomber().setX(1);
        eventHandler.getBomber().setY(1);
//        EventHandler.score = 0;
        eventHandler.getBomber().setImg(Sprite.player_right.getFxImage());
        eventHandler.addEntity(eventHandler.getBomber());

    }
}
