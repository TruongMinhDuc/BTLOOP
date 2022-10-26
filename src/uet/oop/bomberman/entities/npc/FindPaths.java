package uet.oop.bomberman.entities.npc;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Map;
import java.util.Random;

public class FindPaths {

    public int simplePath(Enemy enemy, int direction) {
        double posX = (double) Math.round(enemy.getX() * 1000) / 1000;
        double posY = (double) Math.round(enemy.getY() * 1000) / 1000;
        //System.out.println(tempX + " " + (int) tempX);
        if (posX == (int) posX && posY == (int) posY) {
            Random rand = new Random(System.currentTimeMillis());
            direction = Math.abs(rand.nextInt() % 4);
        }
        return direction;
    }

    public int chasingPlayer(Enemy enemy, int direction) {
        double bomberPosX = (double) Math.round(BombermanGame.eventHandler.getPlayer().getX() * 1000) / 1000;
        double bomberPosY = (double) Math.round(BombermanGame.eventHandler.getPlayer().getY() * 1000) / 1000;

        double enemyPosX = (double) Math.round(enemy.getX() * 1000) / 1000;
        double enemyPosY = (double) Math.round(enemy.getY() * 1000) / 1000;

        double diffX = bomberPosX - enemy.getX();
        double diffY = bomberPosY - enemy.getY();

        double distance = Math.sqrt(diffX * diffX + diffY * diffY);
        //System.out.println(distance);
        System.out.println(bomberPosY + " " + enemyPosY);
        if (distance > 3) {
            //System.out.println("di tu do");
            enemy.setSpeed(Enemy.enemySpeed);
            //Random rand = new Random(System.currentTimeMillis());
            //direction = Math.abs(rand.nextInt() % 4);
            direction = simplePath(enemy, direction);
            //direction = 2;
        } else {
            //System.out.println("duoi nguoi choi");
            enemy.setSpeed(Enemy.enemySpeed * 1.5);
            //System.out.println(bomberPosX + bomberPosY);
            if ((int) enemyPosY == (int) bomberPosY) {
                if (enemyPosX < bomberPosX) {
//                    System.out.println("di sang phai2");
                    direction = 3;
                } else {
//                    System.out.println("di sang trai2");
                    direction = 2;
                }
            } else if (enemyPosY > bomberPosY) {

                System.out.println("can di len");
                //if (enemyPosY == (int) enemyPosY) {
//                    System.out.println("duoc di xuong2");
                direction = 0;
                //}
            } else {
                System.out.println("can di xuong");
                //if (enemyPosY == (int) enemyPosY) {
//                    System.out.println("duoc di len2");
                direction = 1;
                //}
            }
        }
        return direction;
    }
}