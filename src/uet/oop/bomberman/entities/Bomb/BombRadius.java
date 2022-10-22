package uet.oop.bomberman.entities.Bomb;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EventHandler;
import uet.oop.bomberman.entities.Entity;

import java.util.stream.IntStream;

public class BombRadius extends Entity {
    protected ExplosionRender[] explosionList;
    protected int direction;
    private boolean removable = false;
    boolean last;

    public BombRadius(double x, double y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        explosionList = new ExplosionRender[radius()];
        explosionCreate();

    }

    private int radius() {
        int radius1 = 0;
        int x1 = (int) x;
        int y1 = (int) y;
        while (radius1 < EventHandler.bombRadius) {
            if (direction == 0) y1--;
            if (direction == 1) x1++;
            if (direction == 2) y1++;
            if (direction == 3) x1--;
            if (EventHandler.map[y1][x1] == ' ') {
                radius1++;
            }
            if (EventHandler.map[y1][x1] == '#') {
                break;
            }
            if (EventHandler.map[y1][x1] == '*') {
                radius1++;
            }
            if (EventHandler.map[y1][x1] != '#' && EventHandler.map[y1][x1] != ' ') {
                for (Entity temp : BombermanGame.eventHandler.getEntities()) {
                    if (temp.getX() == x1 && temp.getY() == y1) {
                        temp.setRemovable(true);
                        break;
                    }
                }
                break;
            }

        }

        return radius1;

    }

    public void explosionCreate() {
        int x1 = (int) x;
        int y1 = (int) y;
        for (int i = 0; i < explosionList.length; i++) {
            last = i == explosionList.length - 1;
            switch (direction) {
                case 0:
                    y1--;
                    break;
                case 1:
                    x1++;
                    break;
                case 2:
                    y1++;
                    break;
                case 3:
                    x1--;
                    break;
            }
            explosionList[i] = new ExplosionRender(x1, y1, direction, last);
        }
    }

    public void update(int duration) {
        for (int i = 0; i < explosionList.length; i++) {
            explosionList[i].update(direction, duration);
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        IntStream.range(0, explosionList.length).filter(i -> !removable).forEach(i -> explosionList[i].render(gc));
    }


    @Override
    public void update() {

    }
}
