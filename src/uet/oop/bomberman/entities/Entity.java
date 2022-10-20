package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.util.HashSet;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected double x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected double y;
    protected Image img;
    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity() {

    }
    public Entity(double xUnit, double yUnit, Image img) {
        this.x = xUnit ;
        this.y = yUnit ;
        this.img = img;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }


    //    public void moveUp() {
//        y = y - 10;
//    }
//
//    public void moveDown() {
//        y = y + 10;
//    }
//
//    public void moveLeft() {
//        x = x - 10;
//    }
//
//    public void moveRight() {
//        x = x + 10;
//    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x* Sprite.SCALED_SIZE, y* Sprite.SCALED_SIZE );
    }

    public abstract void update();


}
