package uet.oop.bomberman.entities.npc;

import uet.oop.bomberman.graphics.Sprite;

import java.util.Map;
import java.util.Random;

public class FindPaths {

    public int simplePath(Enemy enemy, int direction) {
        double dis = 1;
        double posX =enemy.getX() + dis;
                posX = (double) Math.round(posX  * 1000) / 1000;
        double posY = enemy.getY() + dis;
                posY = (double) Math.round(posY  * 1000) / 1000;
        //System.out.println(Math.round(posX -(int) posX));
//
        System.out.println(posX + " " + (int) posX);
//        //return 2;
//        if (posX == (int) posX || posY == (int) posY) {
//            Random rd = new Random(System.currentTimeMillis());
//            int i = Math.abs(rd.nextInt()) % 4;
//            System.out.println("gia tri cua i la " + i);
//            direction = i;
//        }
//        System.out.println(direction);
//        return direction;
        return 3;
    }
}
