package uet.oop.bomberman.entities.npc;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventHandler;

import java.util.ArrayList;
import java.util.Random;

public class FindPaths {
    int[] disX = {0, 0, -1, 1};
    int[] disY = {-1, 1, 0, 0};
    Random rand = new Random();

    public int simplePath(Enemy enemy, int direction) {
        double posX = (double) Math.round(enemy.getX() * 100) / 100;
        double posY = (double) Math.round(enemy.getY() * 100) / 100;
        //System.out.println(tempX + " " + (int) tempX);
        if (posX == (int) posX && posY == (int) posY) {
            //Random rand = new Random();
            direction = Math.abs(rand.nextInt() % 4);
        }
        return direction;
    }

    public int chasingPlayer(Enemy enemy, int direction) {
        double bomberPosX = (double) Math.round(BombermanGame.eventHandler.getBomber().getX() * 100) / 100;
        double bomberPosY = (double) Math.round(BombermanGame.eventHandler.getBomber().getY() * 100) / 100;

        double enemyPosX = (double) Math.round(enemy.getX() * 100) / 100;
        double enemyPosY = (double) Math.round(enemy.getY() * 100) / 100;

        double X = bomberPosX - enemyPosX;
        double Y = bomberPosY - enemyPosY;

        double dis = Math.sqrt(X * X + Y * Y);
        //System.out.println(distance);
        //System.out.println(bomberPosY + " " + enemyPosY);
        if (dis > 4) {
            //System.out.println("di tu do");
            enemy.setSpeed(Enemy.enemySpeed);
            direction = simplePath(enemy, direction);
            //direction = 2;
        } else {
            //System.out.println("duoi nguoi choi");
            //System.out.println(bomberPosX + bomberPosY);
            enemy.setSpeed(Enemy.enemySpeed * 1.5);
            if (enemyPosX == (int) enemyPosX && enemyPosX == (int) enemyPosY) {

                if ((int) enemyPosY == (int) bomberPosY) {
                    if (enemyPosX < bomberPosX) {
//                    System.out.println("di sang phai2");
                        direction = 3;
                    } else {
//                    System.out.println("di sang trai2");
                        direction = 2;
                    }
                } else if (enemyPosY > bomberPosY) {
                    //System.out.println("can di len");
                    //if (enemyPosY == (int) enemyPosY) {
//                    System.out.println("duoc di xuong2");
                    direction = 0;
                    //}
                } else {
                    //System.out.println("can di xuong");
                    //if (enemyPosY == (int) enemyPosY) {
//                    System.out.println("duoc di len2");
                    direction = 1;
                    //}
                }
            }
        }
        return direction;
    }

    public int kondoriaMove(Enemy enemy, int direction) {
        double enemyPosX = (double) Math.round(enemy.getX() * 100) / 100;
        double enemyPosY = (double) Math.round(enemy.getY() * 100) / 100;
        if (enemyPosX == (int) enemyPosX && enemyPosY == (int) enemyPosY) {
            ArrayList<Integer> directionList = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                if (moveThroughBrick(enemy, i)) {
                    directionList.add(i);
                }
            }
                int i = Math.abs(rand.nextInt() % directionList.size());
                return directionList.get(i);
        } else {
            return direction;
        }
    }

    public boolean moveThroughBrick(Enemy enemy, int direction) {
        double enemyPosX = (double) Math.round(enemy.getX() * 100) / 100;
        double enemyPosY = (double) Math.round(enemy.getY() * 100) / 100;

        enemyPosX += disX[direction];
        enemyPosY += disY[direction];
        if (EventHandler.map[(int) enemyPosY][(int) enemyPosX] != '#') return true;
        return false;
    }
}