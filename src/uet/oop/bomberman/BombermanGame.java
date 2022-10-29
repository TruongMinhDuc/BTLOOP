package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.entities.Bomb.Bomb;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.shape.Rectangle;

import java.awt.*;
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


    public Text playerLife;
    public Text showLevel;
    public Text showBomb;
    public Text showScore;
    private List<Text> textList = new ArrayList<>();
    public static Group root = new Group();
    public static Scene oScene;
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
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root, (WIDTH + 6) * Sprite.SCALED_SIZE, HEIGHT * Sprite.SCALED_SIZE, Color.GREY);

        try {
            newGame();
            createBomberStatus();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Them scene vao stage
        stateScene();
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Bomberman");

        root.getChildren().addAll(textList);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
                eventHandler.render();
                eventHandler.update();
                if(Bomber.isLose()){
                    gameOverScene("game over");
                    stage.setScene(oScene);
                }
            }
        };

        timer.start();


        controller.handle(scene);
    }


    public void newGame() throws FileNotFoundException {
        eventHandler = new EventHandler();
        controller = new Controller();
        eventHandler.getPlayer().setLife(3);
        eventHandler.setLevel(1);
        eventHandler.getGameLevel().createMap(1);
        eventHandler.setShowLevel(eventHandler.getLevel());
    }

    public void update() {
        playerLife.setText(String.valueOf(eventHandler.getPlayer().getLife()));
        showLevel.setText(String.valueOf(eventHandler.getShowLevel()));
        showBomb.setText(String.valueOf(Bomb.maxBomb - Bomber.getBombsList().size()));
        showScore.setText(String.valueOf(EventHandler.getScore()));
    }
    public static void stateScene() {
        Font font = Font.font("System Bold", FontWeight.EXTRA_BOLD, 40);
        Color color = Color.WHITE;

        Rectangle rScene = new Rectangle(0,0,(WIDTH)* Sprite.SCALED_SIZE, HEIGHT* Sprite.SCALED_SIZE);
        rScene.setFill(Color.BLACK);

        Text levelText = new Text(420,198, "Level");
        levelText.setFill(color);
        levelText.setFont(font);

        Text showLevel1 = new Text(550, 198, String.valueOf(eventHandler.getShowLevel()));
        showLevel1.setFill(color);
        showLevel1.setFont(font);

        Group lgroup = new Group();
        lgroup.getChildren().addAll(rScene,levelText, showLevel1);

        root.getChildren().add(lgroup);

        FadeTransition fadeTransition = new FadeTransition(new Duration(1000), lgroup);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(1);
        fadeTransition.setOnFinished(evt->lgroup.setVisible(false));
        fadeTransition.play();
    }

    public void createBomberStatus() {
        Font font = Font.font("System Bold", FontWeight.EXTRA_BOLD, 25);
        Color color = Color.WHITE;
// bomber life
        Text lifeText = new Text(1020, 90, "Left: ");
        lifeText.setFill(color);
        lifeText.setFont(font);
        textList.add(lifeText);

        playerLife = new Text(1110, 90, String.valueOf(eventHandler.getPlayer().getLife()));
        playerLife.setFill(color);
        playerLife.setFont(font);
        textList.add(playerLife);
// Bomber level
        Text levelText = new Text(1020, 30, "Level: ");
        levelText.setFill(color);
        levelText.setFont(font);
        textList.add(levelText);

        showLevel = new Text(1110, 30, String.valueOf(eventHandler.getShowLevel()));
        showLevel.setFill(color);
        showLevel.setFont(font);
        textList.add(showLevel);
// Bomber bomb
        Text bombText = new Text(1020, 150, "Bomb: ");
        bombText.setFill(color);
        bombText.setFont(font);
        textList.add(bombText);

        showBomb = new Text(1110, 150, String.valueOf(Bomb.maxBomb - Bomber.getBombsList().size()));
        showBomb.setFill(color);
        showBomb.setFont(font);
        textList.add(showBomb);
// Bomber score
        Text scoreText = new Text(1020, 210, "sore: ");
        scoreText.setFill(color);
        scoreText.setFont(font);
        textList.add(scoreText);

        showScore = new Text(1110, 210, String.valueOf(EventHandler.getScore()));
        showScore.setFill(color);
        showScore.setFont(font);
        textList.add(showScore);
    }

    private void gameOverScene(String string) {
        Group newGroup = new Group();
        Text textOver = new Text(420, 198, string);

        textOver.setFont(Font.font("System Bold", FontWeight.BOLD, 80));
        textOver.setFill(Color.WHITE);

        newGroup.getChildren().add(textOver);
        oScene = new Scene(newGroup, (WIDTH + 6) * Sprite.SCALED_SIZE, HEIGHT * Sprite.SCALED_SIZE, Color.BLACK);
    }
}
