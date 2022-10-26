package uet.oop.bomberman.entities.npc;

import uet.oop.bomberman.graphics.Sprite;

import java.util.Map;
import java.util.Random;

public class FindPaths {

    public int simplePath(Enemy enemy, int direction) {
        double posX = (double) Math.round(enemy.getX() * 1000) / 1000;
        double posY = (double) Math.round(enemy.getY() * 1000) / 1000;
        //System.out.println(tempX + " " + (int) tempX);
        if (posX == (int) posX && posY == (int) posY) {
            Random rand = new Random();
            direction = rand.nextInt(4);
        }
        return direction;
    }
}