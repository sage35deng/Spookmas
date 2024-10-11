package src;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class Sanata extends Characters {

    // Create an AI Santa that has two modes, one that roams around aimlessly and
    // one that pathfinds towards the player increasing in difficulty

    int sanata_x = 0;
    int sanata_y = 0;
    int sanataNumber = 1;
    int santaCounter = 0;
    int buffertime = 0;

    int targetX = 23*48;
    int targetY = 9*48;

    public String sanataMap = "downStairs";
    public String sanataMode = "roaming";
    public int secStart;

    public Sanata(GamePanel gp) {
        super(gp);
        setDirection = "down";
        speed = 1;
        getSanataImage();
    }

    public void defaultSanata() {
        // Set his default position at the beginning of the game
        sanata_x = 23 * 48;
        sanata_y = 11 * 48;

        speed = 1;
        setDirection = "right";
        sanataMap= "downStairs";
        targetX = 23*48;
        targetY = 9*48;
        onPath=true;
        secStart = gp.sec;
    }

    public void getSanataImage() {
        // Load the player images
        try {
            // Load images
            up1 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/sanataSprites/santa_sprite1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/sanataSprites/santa_sprite2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/sanataSprites/santa_sprite3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/sanataSprites/santa_sprite4.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/sanataSprites/santa_sprite1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/sanataSprites/santa_sprite1.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/sanataSprites/santa_sprite1.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/sanataSprites/santa_sprite1.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/sanataSprites/santa_sprite1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/sanataSprites/santa_sprite1.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/sanataSprites/santa_sprite1.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/sanataSprites/santa_sprite1.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/sanataSprites/santa_sprite1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/sanataSprites/santa_sprite1.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/sanataSprites/santa_sprite1.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/src/pictures/sanataSprites/santa_sprite1.png"));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            // CATCH ERRORS
            e.printStackTrace();
        }
    }

    public static int rng(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public boolean OnPath = true;

    // This is where we draw the character
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        // update the santa animation
        if (sanataNumber == 1) {
            image = right1;
        } else if (sanataNumber == 2) {
            image = right2;
        } else if (sanataNumber == 3) {
            image = right3;
        } else if (sanataNumber == 4) {
            image = right4;
        }

        if (OnPath == true&&lostAggro==false) {
            System.out.println("HUNTING");
            g2.drawImage(image, sanata_x - gp.player.worldX + gp.player.screenX, sanata_y - gp.player.worldY + gp.player.screenY, gp.tileSize * 2, gp.tileSize * 2, null);
        }
    }

    int death=0;
    boolean lostAggro;
    public void update() {

        if (gp.sec - secStart > rng((25 - gp.gSpawn.spawns), (40 - gp.gSpawn.spawns))) {
            onPath = true;
            gp.playSEffect(4);
            int rand = rng(1, 4);
            if (rand == 1){
                sanata_x = gp.player.worldX + rng(5 * 32, 7 * 32);
                sanata_y = gp.player.worldY;
                targetX = gp.player.worldX;
            targetY = gp.player.worldY;}
            if (rand == 2){
                sanata_x = gp.player.worldX - rng(5 * 32, 7 * 32);
                sanata_y = gp.player.worldY;
                targetX = gp.player.worldX;
            targetY = gp.player.worldY;}
            if (rand == 3){
                sanata_y = gp.player.worldY + rng(5 * 32, 7 * 32);
                sanata_x = gp.player.worldX;
                targetX = gp.player.worldX;
            targetY = gp.player.worldY;}
            if (rand == 4){
                sanata_y = gp.player.worldY - rng(5 * 32, 7 * 32);
                sanata_x = gp.player.worldX;
                targetX = gp.player.worldX;
                targetY = gp.player.worldY;
            }
                
            gp.playSEffect(4);
            sanataMap = gp.tileM.currentMap;
            secStart = gp.sec;
            lostAggro=false;
        }

        if (gp.player.hiding == true) {
            onPath = false;
            sanata_x=100000000;
            sanata_y=100000000;
        }

        if(onPath==true){
            targetX = gp.player.worldX;
            targetY = gp.player.worldY;
            speed =1;
        }

        if(onPath==false){
            speed=0;
        }
        
        if(sanataMap.equals(gp.tileM.currentMap)){
            // go pass through pics
            if (sanataNumber == 1) {
                sanataNumber = 2;
            } else if (sanataNumber == 2) {
                sanataNumber = 3;
            } else if (sanataNumber == 3) {
                sanataNumber = 4;
            } else if (sanataNumber == 4) {
                sanataNumber = 1;
            }

            if (onPath == true && sanataMap.equals(gp.tileM.currentMap)) {
                targetX = gp.player.worldX;
                targetY = gp.player.worldY;
                speed = 1;
                if (targetX < sanata_x) {
                    if(sanata_y/48!=gp.player.worldY){
                        speed=3;
                    }
                    sanata_x -= speed;
                } else if (targetX > sanata_x) {
                    if(sanata_y/48!=gp.player.worldY){
                        speed=3;
                    }
                    sanata_x += speed;
                }
                if (targetY < sanata_y) {
                    if(sanata_x/48!=gp.player.worldX){
                        speed=3;
                    }
                    sanata_y -= speed;
                } else if (targetY > sanata_y) {
                    if(sanata_x/48!=gp.player.worldX){
                        speed=3;
                    }
                    sanata_y += speed;
                }

                
            }

            if(sanata_x/48== gp.player.worldX/48 && sanata_y/48==gp.player.worldY/48){
                if(sanataMap.equals(gp.tileM.currentMap)){
                    death++;
                }
            }
        }
    }
}