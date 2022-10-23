package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.MapEntities.Brick;
import uet.oop.bomberman.entities.MapEntities.Grass;
import uet.oop.bomberman.entities.MapEntities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TODO: đọc từ file các kí tự text thêm vào obj
public class MapGen {
    public MapGen(EventHandler eventHandler) {
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
        while(sc.hasNextLine()) {
            String tmp = sc.nextLine();
            //System.out.println(tmp.length());
            //System.out.println(row);
            for(int j = 0; j < tmp.length(); j++) {
                EventHandler.map[he][j] = tmp.charAt(j);
            }
            he++;
        }
        //System.out.println(BombermanGame.map[][16]);

        for(int i = 0; i < BombermanGame.HEIGHT; i++) {
            for(int j = 0; j < BombermanGame.WIDTH; j++) {
                if(EventHandler.map[i][j] == '#') {
                    Entity obj;
                    obj = new  Wall(j, i, Sprite.wall.getFxImage());
                    EventHandler.addStillObject(obj);
                    //EventHandler.addEntity(obj);
                } else {
                    Entity obj;
                    obj = new Grass(j, i, Sprite.grass.getFxImage());
                    EventHandler.addStillObject(obj);
                    //EventHandler.addEntity(obj);
                }
                if(EventHandler.map[i][j] == '*') {
                        Entity obj = new Brick(j, i, Sprite.brick.getFxImage());
                        EventHandler.addStillObject(obj);
                        EventHandler.addEntity(obj);
                }
            }
        }


        
    }
}
