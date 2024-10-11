package src.tile;
import java.awt.image.BufferedImage;

public class Gift  {
    public BufferedImage image;
    public boolean collision = false;

    public boolean teleport = false;
    public boolean closet = false;
    public int giftX;
    public int giftY;
    public int giftID;

    public Gift(int giftX, int giftY){
        this.giftX = giftX;
        this.giftY = giftY;
    }
}




