package src;

import java.awt.image.BufferedImage;

import javax.swing.text.html.parser.Entity;

import java.awt.Rectangle;

public class Characters {

    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    // Allows us to store our image data
    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2,
            right3, right4, not_moving;
    public String setDirection;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
    public boolean teleportOn = false;
    public boolean onPath = false;

    public Characters(GamePanel gp) {
        this.gp = gp;
    }

}