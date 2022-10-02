package uet.oop.bomberman;

import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.Scene;

public class Controller {
    public boolean right, left, up, down, space;
    public Controller() {
        up = false;
        down = false;
        left = false;
        right = false;
        space = false;
    }

    public void handle(Scene input) {
        input.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    up = true;
                    System.out.println("up");
                    break;
                case S:
                    down = true;
                    System.out.println("down");
                    break;
                case A:
                    left = true;
                    System.out.println("left");
                    break;
                case D:
                    right = true;
                    System.out.println("right");
                    break;
            }
        });

        input.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case W:
                    up = false;
                    break;
                case S:
                    down = false;
                    break;
                case A:
                    left = false;
                    break;
                case D:
                    right = false;
                    break;
            }
        });
    }

}
