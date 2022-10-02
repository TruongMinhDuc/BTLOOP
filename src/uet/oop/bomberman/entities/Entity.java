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
    protected int x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;
    protected Image img;
    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity() {

    }
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit;
        this.y = yUnit;
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
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
        gc.drawImage(img, x * Sprite.SCALED_SIZE, y * Sprite.SCALED_SIZE);
    }

    public abstract void update();
// hashset for something else in the future update
//    public HashSet<String> movingHandle(Entity moving) {
//        Image objFrame = moving.getImg();
//        HashSet<String> ans = null;
//        if(objFrame != null) {
//            ans = new HashSet<String>();
//            int cooX = (int) objFrame.getWidth();
//            int cooY = (int) objFrame.getHeight();
//
//            PixelReader read = objFrame.getPixelReader();
//
//            int pix;
//            for(int y = 0; y < cooY; y++) {
//                for(int x = 0; x < cooX; x++) {
//                    final  int argb = read.getArgb(x,y);
//                    pix = (argb >> 24) & 0xff;
//                    if(pix != 0) {
//                        ans.add((int) (moving.getX() * 32) + x + "," + ((int) (moving.getY() * 32) - y));
//                    }
//                }
//            }
//        }
//        return ans;
//    }

}
