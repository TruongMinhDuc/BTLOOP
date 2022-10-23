package uet.oop.bomberman.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventHandler;
import uet.oop.bomberman.entities.Entity;

import java.util.stream.IntStream;

public class BombRadius extends Entity {
    protected ExplosionRender[] explosionImg;
    protected int direction;
    private boolean removable = false;
    boolean time;

    public BombRadius(double x, double y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        explosionImg = new ExplosionRender[radius()];
        explosionCreate();

    }

    private int radius() {
        int rad = 0;
        int xPos = (int) x;
        int yPos = (int) y;
        while (rad < Bomb.bombRadius) {
            if (direction == 0) {
                yPos--;
            }
            if (direction == 1) {
                xPos++;
            }
            if (direction == 2) {
                yPos++;
            }
            if (direction == 3) {
                xPos--;
            }
            if (EventHandler.map[yPos][xPos] == ' ') {
                rad++;
            }
            if (EventHandler.map[yPos][xPos] == '#') {
                break;
            }
            if (EventHandler.map[yPos][xPos] == '*') {
                rad++;
            }
            if (EventHandler.map[yPos][xPos] != '#' && EventHandler.map[yPos][xPos] != ' ') {
                for (Entity temp : BombermanGame.eventHandler.getEntitiesList()) {
                    if (temp.getX() == xPos && temp.getY() == yPos) {
                        temp.setRemovable(true);
                        break;
                    }
                }
                break;
            }

        }

        return rad;

    }

    public void explosionCreate() {
        int xPos = (int) x;
        int yPos = (int) y;
        for (int i = 0; i < explosionImg.length; i++) {
            time = i == explosionImg.length - 1;
            switch (direction) {
                case 0:
                    //up
                    yPos--;
                    break;
                case 1:
                    //right
                    xPos++;
                    break;
                case 2:
                    //down
                    yPos++;
                    break;
                case 3:
                    //left
                    xPos--;
                    break;
            }
            explosionImg[i] = new ExplosionRender(xPos, yPos, direction, time);
        }
    }

    public void update(int duration) {
        for (int i = 0; i < explosionImg.length; i++) {
            explosionImg[i].update(direction, duration);
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        IntStream.range(0, explosionImg.length).filter(i -> !removable).forEach(i -> explosionImg[i].render(gc));
    }


    @Override
    public void update() {

    }
}
