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

    public boolean removable = false;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity() {

    }

    public Entity(double xUnit, double yUnit, Image img) {
        this.x = xUnit;
        this.y = yUnit;
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

    public void setRemovable(boolean removable) {
        this.removable = removable;
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
        gc.drawImage(img, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }

    public abstract void update();

    public HashSet<String> blockEntity(Entity obj) {
        Image imgObj = obj.getImg();
        HashSet<String> block = null;
        if (imgObj != null) {
            block = new HashSet<String>();
            int W = (int) imgObj.getWidth();
            int H = (int) imgObj.getHeight();

            PixelReader reader = imgObj.getPixelReader();
            int tmp;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    int argb = reader.getArgb(j, i);
                    tmp = (argb >> 24) & 0xff;
                    if (tmp != 0) {
                        block.add((int) (obj.getX() * 32) + j + "," + ((int) (obj.getY() * 32) - i));
                    }
                }
            }
        }
        return block;
    }

}
