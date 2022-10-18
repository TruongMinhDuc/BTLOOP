package uet.oop.bomberman;

import javafx.scene.chart.Chart;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TODO: đọc từ file các kí tự text thêm vào obj
public class MapGen {
    public static int level = 1;
    //public static char[][] map = new char[BombermanGame.HEIGHT][BombermanGame.WIDTH];

    public static void createMap() throws FileNotFoundException {
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
        int col = 0;
        while(sc.hasNextLine()) {
            String tmp = sc.nextLine();
            //System.out.println(tmp.length());
            //System.out.println(row);
            for(int j = 0; j < tmp.length(); j++) {
                BombermanGame.map[j][col] = tmp.charAt(j);
            }
            col++;
        }
        //System.out.println(BombermanGame.map[1][2]);

        for(int i = 0; i < BombermanGame.WIDTH; i++) {
            for(int j = 0; j < BombermanGame.HEIGHT; j++) {
                if(BombermanGame.map[i][j] == '#') {
                    Entity obj;
                    obj = new  Wall(i, j, Sprite.wall.getFxImage());
                    BombermanGame.stillObjects.add(obj);
                } else {
                    Entity obj;
                    obj = new Grass(i, j, Sprite.grass.getFxImage());
                    BombermanGame.stillObjects.add(obj);
                }
            }
        }


        
    }
}
