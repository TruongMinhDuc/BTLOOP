package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    //render
    public static GraphicsContext gc;

    public static Canvas canvas;
    public static Controller controller;
    public static EventHandler eventHandler;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene.fxml"));
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();


        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root, 37 * Sprite.SCALED_SIZE, HEIGHT * Sprite.SCALED_SIZE, Color.BLACK );

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Bomberman");


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
              eventHandler.render();
              eventHandler.update();
            }
        };
        timer.start();
        try {
            newGame();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        controller.handle(scene);

    }

    public void newGame() throws FileNotFoundException {
            eventHandler = new EventHandler();
            controller = new Controller();
            eventHandler.getPlayer().setLife(3);
            eventHandler.setLevel(1);
            eventHandler.getGameLevel().createMap(1);
    }


}
