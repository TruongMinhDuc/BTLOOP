package uet.oop.bomberman.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventHandler;
import uet.oop.bomberman.entities.Entity;

import java.util.stream.IntStream;

public class BombRadius extends Entity {
    protected ExplosionRender[] explosionFrame;
    protected int direction;
    private boolean removable = false;
    boolean time;

    public BombRadius(double x, double y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        explosionFrame = new ExplosionRender[radius()];
        explosionSize();

    }

    private int radius() {
        int blast = 0;
        int xPos = (int) x;
        int yPos = (int) y;
        while (blast < Bomb.blastLength) {
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
                blast++;
            }
            if (EventHandler.map[yPos][xPos] == '#') {
                break;
            }
            if (EventHandler.map[yPos][xPos] == '*') {
                blast++;
            }
            if(EventHandler.map[yPos][xPos] == 'B') {
                blast++;
            }
            if (EventHandler.map[yPos][xPos] != '#' && EventHandler.map[yPos][xPos] != ' ') {
                for (Entity temp :  BombermanGame.eventHandler.getEntitiesList()) {
                    if (temp.getX() == xPos && temp.getY() == yPos) {
                        temp.setRemovable(true);
                        break;
                    }
                }
                break;
            }

        }

        return blast;

    }

    public void explosionSize() {
        int xPos = (int) x;
        int yPos = (int) y;
        for (int i = 0; i < explosionFrame.length; i++) {
            time = i == explosionFrame.length - 1;
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
            explosionFrame[i] = new ExplosionRender(xPos, yPos, direction, time);
        }
    }

    public void update(int duration) {
        for (int i = 0; i < explosionFrame.length; i++) {
            explosionFrame[i].update(direction, duration);
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        IntStream.range(0, explosionFrame.length).filter(i -> !removable).forEach(i -> explosionFrame[i].render(gc));
    }


    @Override
    public void update() {

    }
}
